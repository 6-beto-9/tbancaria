package com.java360.tbancaria.core.Transacao.service;

import com.java360.tbancaria.core.Conta.model.Conta;
import com.java360.tbancaria.core.Conta.repository.ContaRepository;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoRequest;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class TransacaoService {

    private final ContaRepository contaRepository;

    public TransacaoService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Transactional
    public TransacaoResponse realizarTransacao(TransacaoRequest request) {
        if (request.valor() == null || request.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor da transferência deve ser maior que zero.");
        }

        Conta contaOrigem = contaRepository.findById(request.idContaOrigem())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));

        Conta contaDestino = contaRepository.findById(request.idContaDestino())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada."));

        if (contaOrigem.getSaldo().compareTo(request.valor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a transferência.");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(request.valor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(request.valor()));

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        return new TransacaoResponse(
                "SUCESSO",
                request.valor(),
                LocalDateTime.now(),
                contaOrigem.getIdConta(),
                contaDestino.getIdConta()
        );
    }
}