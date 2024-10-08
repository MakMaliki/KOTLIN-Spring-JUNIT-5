package com.malik.api.spring.kotlinspringboot.service

import com.malik.api.spring.kotlinspringboot.datasource.BankDataSource
import com.malik.api.spring.kotlinspringboot.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> = dataSource.retrieveBanks()
    fun getBanks(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.createBank(bank)
    fun updateBank(bank: Bank): Bank = dataSource.updateBank(bank)
    fun deleteBank(accountNumber: String) = dataSource.deleteBank(accountNumber)

}