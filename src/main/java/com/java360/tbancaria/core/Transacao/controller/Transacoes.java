package com.java360.tbancaria.core.Transacao.controller;

import  com.java360.tbancaria.core.Transacao.dtos.TransacaoRequest;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoResponse;
import com.java360.tbancaria.core.Transacao.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class Transacoes {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoResponse> realizarTransacao(@Valid @RequestBody TransacaoRequest request) {
        TransacaoResponse response = transacaoService.transferirValor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}