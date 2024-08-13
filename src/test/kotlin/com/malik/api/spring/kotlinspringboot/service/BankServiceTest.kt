package com.malik.api.spring.kotlinspringboot.service

import com.malik.api.spring.kotlinspringboot.datasource.BankDataSource
import com.malik.api.spring.kotlinspringboot.model.Bank
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest {
    private val dataSource:BankDataSource = mockk(relaxed = true)

    private val banksService = BankService(dataSource)

    @Test
    fun `should call its data source to retrive Banks`(){

////Given
//     Mockk is a library for providing mock data for an Object
//      in Mockk when we  specify (relaxed true) it cretes empty objects or lists  or with some itesm for the objects
// If we dont specify relaxed we must specify every {dataSource.retriveBanks} return EmptyList

//        every { dataSource.retrieveBanks() } returns emptyList()
//

        //When

        banksService.getBanks()


        //Then

        verify (exactly = 1) {
            dataSource.retrieveBanks()
        }



    }

}