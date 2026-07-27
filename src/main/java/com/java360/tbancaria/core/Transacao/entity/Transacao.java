package com.java360.tbancaria.core.Transacao.entity;

import com.java360.tbancaria.core.Conta.model.Conta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Transacao")

public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Long idTransacao;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataTransacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta_origem", nullable = false)
    private Conta contaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta_destino", nullable = false)
    private Conta contaDestino;

}