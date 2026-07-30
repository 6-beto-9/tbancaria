package com.java360.tbancaria.core.Conta.controller;

import com.java360.tbancaria.core.Conta.dtos.ContaAberturaRequest;
import com.java360.tbancaria.core.Conta.dtos.ContaResponse;
import com.java360.tbancaria.core.Conta.model.Conta;
import com.java360.tbancaria.core.Conta.repository.ContaRepository;
import com.java360.tbancaria.core.Conta.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/contas")
@RequiredArgsConstructor
public class Contas {

    private final ContaService contaService;
    private final ContaRepository contaRepository;

    @PostMapping
    public ResponseEntity<ContaResponse> abrirConta(@Valid @RequestBody ContaAberturaRequest request) {

            // Envio o DTO com o idTitular, tipoConta, etc., para a Service processar
        ContaResponse response = contaService.abrirConta(request);

            // Retorno HTTP 201 (Created) com os dados da conta aberta no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

        @GetMapping("/{id}")
        public ResponseEntity<ContaResponse> buscarPorId(@PathVariable Long id) {
            ContaResponse conta = contaService.buscarPorId(id);
            return ResponseEntity.ok(conta);
        }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponse> atualizarSaldo(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request) {
        BigDecimal novoSaldo = request.get("saldo");
        ContaResponse contaAtualizada = contaService.atualizarSaldo(id, novoSaldo);
        return ResponseEntity.ok(contaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        contaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}