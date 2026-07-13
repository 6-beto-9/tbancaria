package com.java360.tbancaria.core.Transacao.controller;

import com.java360.tbancaria.core.Transacao.dtos.TransacaoRequest;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transacoes")
public class Transacoes {

    @PostMapping
    public ResponseEntity<TransacaoResponse> realizarTransferencia(@Valid @RequestBody TransacaoRequest request) {

        TransacaoResponse fakeComprovante = new TransacaoResponse(
                1001L,
                request.valor(),
                LocalDateTime.now(),
                request.idContaOrigem(),
                request.idContaDestinatario()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(fakeComprovante);
    }
}