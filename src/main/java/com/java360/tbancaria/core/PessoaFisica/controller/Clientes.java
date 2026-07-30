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


    private final PessoaFisicaService pessoaService; // Injeto a Service (marcada como final)

    @PostMapping
    public ResponseEntity<PessoaFisicaResponse> cadastrar(@Valid @RequestBody PessoaFisicaRequest request) {
        // Chamo a Service para salvar no banco e recebe o Response real
        PessoaFisicaResponse response = pessoaService.cadastrarPessoa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaFisicaRequest request) { // Capturo o ID que veio na URL
        PessoaFisicaResponse response = pessoaService.atualizarPessoa(id, request);
        return ResponseEntity.ok(response); // Retorno HTTP 200 OK com os dados atualizados
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build(); // Retorno HTTP 204 No Content (padrão para exclusão com sucesso)
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponse> buscarPorId(@PathVariable Long id) {
        PessoaFisicaResponse response = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }
}