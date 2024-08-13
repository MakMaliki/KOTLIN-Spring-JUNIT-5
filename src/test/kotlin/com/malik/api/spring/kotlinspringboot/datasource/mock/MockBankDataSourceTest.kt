package com.malik.api.spring.kotlinspringboot.datasource.mock


import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {
    val mockDataSource = MockBankDataSource()


    @Test
    fun `should provide a collection of banks`() {
        //Given
        //  val mockDataSource = MockBankDataSource()


        //When
        val banks = mockDataSource.retrieveBanks()

        //Then
        Assertions.assertThat(banks).isNotEmpty
        Assertions.assertThat(banks.size).isGreaterThanOrEqualTo(3)

    }


    @Test
    fun `should provide some mock data`() {
        //Given
        //  val mockDataSource = MockBankDataSource()


        //When
        val banks = mockDataSource.retrieveBanks()
        //Then

        Assertions.assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        Assertions.assertThat(banks).allMatch { it.trust != 0.0 }
        Assertions.assertThat(banks).allMatch { it.transactionFee != 0 }
        // Assertions.assertThat(banks).allSatisfy { it.accountNumber.isNotBlank() }
    }


}