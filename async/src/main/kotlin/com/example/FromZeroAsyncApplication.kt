package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FromZeroAsyncApplication

fun main(args: Array<String>) {
    runApplication<FromZeroAsyncApplication>(*args)
}
