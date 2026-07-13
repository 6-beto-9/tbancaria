package com.java360.tbancaria.core.PessoaFisica.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaFisicaRequest(

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @Email (message = "O email inserido é inválido.")
        @NotBlank(message = "O email é obrigatório.")
        String email,

        @Size (min = 11, max = 11, message = "O CPF inserido é inválido, ele deve terexatamente 11 dígitos (Apenas números)")
        @NotBlank(message = "O CPF é obrigatório.")
        String cpf

) {}
