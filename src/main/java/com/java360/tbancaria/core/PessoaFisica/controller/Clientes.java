package com.java360.tbancaria.core.PessoaFisica.controller;

import com.java360.tbancaria.core.PessoaFisica.dtos.PessoaFisicaRequest;
import com.java360.tbancaria.core.PessoaFisica.dtos.PessoaFisicaResponse;
import com.java360.tbancaria.core.PessoaFisica.service.PessoaFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class Clientes {


    private final PessoaFisicaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaFisicaResponse> cadastrar(@Valid @RequestBody PessoaFisicaRequest request) {
        PessoaFisicaResponse response = pessoaService.cadastrarPessoa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisicaRequest request) {
        PessoaFisicaResponse response = pessoaService.atualizarPessoa(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponse> buscarPorId(@PathVariable Long id) {
        PessoaFisicaResponse response = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
}