package com.malik.api.spring.kotlinspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class KotlinSpringBootApplication {


@Bean
fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()
}

//Main Entry point to Spring Boot Application
fun main(args: Array<String>) {
    runApplication<KotlinSpringBootApplication>(*args)
}
