package com.malik.api.spring.kotlinspringboot.datasource.network

import com.malik.api.spring.kotlinspringboot.datasource.BankDataSource
import com.malik.api.spring.kotlinspringboot.datasource.network.dto.BankList
import com.malik.api.spring.kotlinspringboot.model.Bank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("network")
class NetworkDataSource(
    @Autowired private val restTemplate: RestTemplate
) : BankDataSource {
    override fun retrieveBanks(): Collection<Bank> {
        val resopnse = restTemplate.getForEntity<BankList>("http://54.193.31.159/banks")

        return resopnse.body?.results
            ?: throw IOException("could not fetch banks from the network")
    }

    override fun retrieveBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun createBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun updateBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }
}