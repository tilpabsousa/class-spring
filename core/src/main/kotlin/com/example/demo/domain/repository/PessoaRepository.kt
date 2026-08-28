package com.example.demo.domain.repository

import com.example.demo.domain.model.Pessoa
import org.springframework.stereotype.Repository

@Repository
class PessoaRepository {
    val listPessoa: MutableList<Pessoa> = mutableListOf()

    fun insert(p: Pessoa) {
        listPessoa.add(p)
        println("$listPessoa")
    }
}
