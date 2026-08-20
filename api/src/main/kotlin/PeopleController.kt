package com.example

import com.example.demo.core.Pessoa
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PeopleController {
    @GetMapping("/insert")
    fun insert() {
        val p = Pessoa("João", 30)
        println("Uma pessoa inserida $p")
    }
}