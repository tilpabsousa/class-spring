package com.example.demo.domain.repository

import com.example.demo.domain.model.Pessoa

class PessoaRepository {

    val listPessoa: MutableList<Pessoa> = mutableListOf()

    fun insert(p: Pessoa) {
        listPessoa.add(p)
        println("$listPessoa")
    }

}