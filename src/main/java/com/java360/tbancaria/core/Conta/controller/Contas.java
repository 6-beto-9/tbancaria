package com.java360.tbancaria.core.Conta.controller;

import com.java360.tbancaria.core.Conta.dtos.ContaAberturaRequest;
import com.java360.tbancaria.core.Conta.dtos.ContaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contas")
public class Contas {

    @PostMapping
    public ResponseEntity<ContaResponse> abrirConta(@Valid @RequestBody ContaAberturaRequest request) {

        ContaResponse fakeResponse = new ContaResponse(
                10L,                             // ID da conta gerado pelo banco
                "Cliente Simulado",                     // Nome do titular que o Service buscaria
                request.tipoConta().toUpperCase(),
                request.saldoInicial()                  // Saldo com o qual a conta começou
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(fakeResponse);
    }
}