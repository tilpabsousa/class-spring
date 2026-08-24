package com.example.demo.domain.service

import com.example.demo.domain.model.Pessoa
import com.example.demo.domain.repository.PessoaRepository
import org.springframework.stereotype.Service

@Service
class PessoaService(val repository: PessoaRepository) {

    fun insertPessoaService(p: Pessoa) {
        repository.insert(p)
    }

}