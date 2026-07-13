package com.java360.tbancaria.core.Conta.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ContaAberturaRequest (

        @NotNull(message = "O id do titular é obrigatório.")
        Long idTitular,

        @Size (min = 8, max = 8)
        @NotBlank(message = "O tipo de conta é obrigatório: 'CORRENTE' ou 'POUPANÇA' ")
        String tipoConta,

        @NotNull(message = "O saldo inicial não pode ser nulo.")
        @PositiveOrZero(message = "O saldo inicial deve ser maior ou igual a zero.")
        BigDecimal saldoInicial

)  {}
