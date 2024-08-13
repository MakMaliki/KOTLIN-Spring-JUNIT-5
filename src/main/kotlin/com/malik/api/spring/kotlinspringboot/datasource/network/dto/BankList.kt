package com.malik.api.spring.kotlinspringboot.datasource.network.dto

import com.malik.api.spring.kotlinspringboot.model.Bank

data class BankList (
    val results: Collection<Bank>
)