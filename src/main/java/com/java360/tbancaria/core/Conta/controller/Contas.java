package com.java360.tbancaria.core.Conta.controller;

import com.java360.tbancaria.core.Conta.dtos.ContaAberturaRequest;
import com.java360.tbancaria.core.Conta.dtos.ContaResponse;
import com.java360.tbancaria.core.Conta.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class Contas {

    private final ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaResponse> abrirConta(@Valid @RequestBody ContaAberturaRequest request) {

            // Envio o DTO com o idTitular, tipoConta, etc., para a Service processar
        ContaResponse response = contaService.abrirConta(request);

            // Retorno HTTP 201 (Created) com os dados da conta aberta no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}