package com.malik.api.spring.kotlinspringboot.datasource.mock

import com.malik.api.spring.kotlinspringboot.datasource.BankDataSource
import com.malik.api.spring.kotlinspringboot.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        Bank("1234", 3.2, 3),
        Bank("898989", 9.2, 12),
        Bank("990009", 4.4, 17),
    )

    override fun retrieveBanks(): Collection<Bank> = banks
    override fun retrieveBank(accountNumber: String): Bank {
        return banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with Account Number$accountNumber")

    }

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("Could not find a bank with Account Number${bank.accountNumber}")
        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

    override fun deleteBank(accountNumber: String) {
        val currentBank = banks.firstOrNull() {
            it.accountNumber == accountNumber
        } ?: throw NoSuchElementException("Could not find a bank with Account Number$accountNumber")

        banks.remove(currentBank)


    }


}