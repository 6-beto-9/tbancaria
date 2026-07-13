package com.java360.tbancaria.core.Conta.entity;

import com.java360.tbancaria.core.Conta.model.Conta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("CORRENTE")

public class ContaCorrente extends Conta {

    @Column(name = "limite_Conta", precision = 12, scale = 2)
    private BigDecimal limiteConta;

    @Column(name = "tarifa_mensal", precision = 12, scale = 2)
    private BigDecimal tarifaMensal;

}
