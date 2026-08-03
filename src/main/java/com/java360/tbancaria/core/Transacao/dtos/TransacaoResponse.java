package com.java360.tbancaria.core.Transacao.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacaoResponse (

        String status,
        BigDecimal valor,
        LocalDateTime dataTransacao,
        Long idContaOrigem,
        Long idContaDestinatario

) {}
