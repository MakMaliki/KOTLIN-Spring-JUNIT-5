package com.malik.api.spring.kotlinspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootApplication

//Main Entry point to Spring Boot Application
fun main(args: Array<String>) {
    runApplication<KotlinSpringBootApplication>(*args)
}
