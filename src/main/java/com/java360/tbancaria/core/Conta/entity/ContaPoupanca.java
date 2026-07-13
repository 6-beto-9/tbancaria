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
@DiscriminatorValue("POUPANÇA")

public class ContaPoupanca extends Conta {

    @Column(name = "taxa_rendimento", precision = 5, scale = 4) // Ex: 0.0050 (0.5%)
    private BigDecimal taxaRendimento;

    @Column(name = "salario_poupanca", precision = 12, scale = 2)
    private BigDecimal salarioPoupanca;

}
