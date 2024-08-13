package com.malik.api.spring.kotlinspringboot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.malik.api.spring.kotlinspringboot.model.Bank
import jdk.jfr.ContentType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    //Spring way of DI // It creates mockMvc object and Object Mapper
    //Here we passed them as a parameter and used constructor parameter
//    @Autowired
//    lateinit var mockMvc: MockMvc
//
//    @Autowired
//    lateinit var objectMapper:ObjectMapper

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all banks`() {
            //Wjem /Then
            mockMvc.get("/api/banks")
                .andDo { print() }
                //Then
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") { value("1234") }

                }
        }
    }


    @Nested
    @DisplayName("GET /api/bank/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given Account Number`() {
            val accountNumber = 1234
            //When /Then
            mockMvc.get("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") { value("3.2") }
                    jsonPath("$.transactionFee") { value("3") }
                }


        }

        @Test
        fun `should return NOT Found if the Account Number does not Exist`() {
            //given
            val accountNumber = "does_not_exist"
            val baseUrl = "/api/banks"
            //When /Then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo { print() }

                //Then
                .andExpect {
                    status {
                        isNotFound()
                    }
                }

        }


    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostBanks {
        val baseUrl = "/api/banks"

        @Test
        fun `should add the a new bank`() {
            //Given

            val newBank = Bank("acc123", 31.3, 2)

            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)

            }
            //Then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value("acc123") }
                    jsonPath("$.trust") { value("31.3") }
                    jsonPath("$.transactionFee") { value("2") }
                }


            mockMvc.get("$baseUrl/${newBank.accountNumber}")
                .andExpect {
                    content {
                        json(objectMapper.writeValueAsString(newBank))
                    }
                }

        }

        @Test
        fun `should return BAD REQUEST if a bank with given account number Exists`() {
            //Given

            val invalidBank = Bank("8393s0s", 9.2, 12)

            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
            //When /Then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }

                }
        }

    }


    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExisitingBank {
        val baseUrl = "/api/banks"


        @Test
        fun `should update an Existing Bank`() {
            //Given

            val updatedBank = Bank("1234", 3.6, 8)

            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)

            }
            //When /Then
            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status {
                        isOk()
                    }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        //For comparing larger objects with so many fields
                        json(objectMapper.writeValueAsString(updatedBank))
                    }

//so we did the   json(objectMapper.writeValueAsString(uodatedBank)) so its ok to not do bottom line code
//                    jsonPath("$.accountNumber") { value("acc123") }
//                    jsonPath("$.trust"){value("31.3")}
//                    jsonPath("$.transactionFee"){value("2")}
                }
            mockMvc.get("$baseUrl/${updatedBank.accountNumber}")
                .andExpect {
                    content {
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }
        }


        @Test
        fun `should return Bad REQUEST  if no bank with Given Account Number exitsts`() {
            //given
            val invalidBank =Bank("does_not_exist",1.0,1)

            val baseUrl = "/api/banks"
            //When /Then

            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)

            }

            performPatchRequest
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }

                }


        }

    }



    @Nested
    @DisplayName("DELETE /api/banks/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistBank {
        val baseUrl = "/api/banks"

        @Test
        fun `should delete the bank with the given Account Number`() {
            //When
            val accountNumber = 1234
            //When /Then
            mockMvc.delete("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status {isNoContent() }
                }
            //When /Then
            mockMvc.get("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }


        }

        @Test
        fun `should return NOT FOUND if no Bank with given Account Number exists`() {
            //When
            val invalidAccountNumber = "does_not_exist"
            //When /Then
            mockMvc.delete("/api/banks/$invalidAccountNumber")
                .andDo { print() }
                .andExpect {
                    status {isNotFound() }
                }
        }


    }
}