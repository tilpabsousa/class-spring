package com.example.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PeopleAsyncController() {
    @GetMapping("/getdata")
    fun getData() {
        println("async data contoller")
    }
}
