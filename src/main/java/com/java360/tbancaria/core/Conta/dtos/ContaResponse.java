package com.java360.tbancaria.core.Conta.dtos;

import java.math.BigDecimal;

public record ContaResponse (

    Long idConta,
    String nomeTitular,
    String tipoConta,
    BigDecimal saldo

) {}
