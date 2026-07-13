package com.java360.tbancaria.core.PessoaFisica.dtos;

public record PessoaFisicaResponse(

        Long id,
        String nome,
        String email,
        String cpf

        ) {}