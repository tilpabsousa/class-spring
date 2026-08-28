package com.example.controller

import com.example.demo.domain.model.Pessoa
import com.example.demo.domain.service.PessoaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PeopleController(val pessoaService: PessoaService) {
    @GetMapping("/insert")
    fun insert() {
        val p = Pessoa("João", 30)
        pessoaService.insertPessoaService(p)
        println("Uma pessoa inserida $p")
    }
}
