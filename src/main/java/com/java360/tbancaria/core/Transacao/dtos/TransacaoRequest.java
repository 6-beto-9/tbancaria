package com.java360.tbancaria.core.Transacao.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransacaoRequest (

        @NotNull(message = "A conta de origem é obrigatória.")
        Long idContaOrigem,

        @NotNull(message = "A conta de destino é obrigatória.")
        Long idContaDestinatario,

        @NotNull(message = "O valor não pode ser nulo.")
        @Positive(message = "O valor deve ser superior a 0.")
        BigDecimal valor

) {}
