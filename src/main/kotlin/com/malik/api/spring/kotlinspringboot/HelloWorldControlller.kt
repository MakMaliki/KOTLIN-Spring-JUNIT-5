package com.malik.api.spring.kotlinspringboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/hello")
class HelloWorldControlller {

//    @GetMapping("/springboot")
//    fun helloWorld():String=  "Hello, This is a REST endpoint"

    @GetMapping
    fun helloWorld():String=  "Hello, This is a REST endpoint"

    @GetMapping("/malik")
    fun hellomalik():String=  "Hello, This is a Message from Malik"



}