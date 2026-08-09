package com.java360.tbancaria.core.Transacao.controller;

import  com.java360.tbancaria.core.Transacao.dtos.TransacaoRequest;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoResponse;
import com.java360.tbancaria.core.Transacao.service.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
public class Transacoes {

    private final TransacaoService transacaoService;

    public Transacoes(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/transferir")
    public ResponseEntity<TransacaoResponse> transferir(@RequestBody TransacaoRequest request) {
        TransacaoResponse response = transacaoService.realizarTransacao(request);
        return ResponseEntity.ok(response);
    }
}