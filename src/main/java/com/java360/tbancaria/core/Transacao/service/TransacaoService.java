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
        // Valido valor da transferência
        if (request.valor() == null || request.valor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor da transferência deve ser maior que zero.");
        }

        // Busco contas de origem e destino
        Conta contaOrigem = contaRepository.findById(request.idContaOrigem())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));

        Conta contaDestino = contaRepository.findById(request.idContaDestino())
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada."));

        // Valido se há saldo suficiente
        if (contaOrigem.getSaldo().compareTo(request.valor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a transferência.");
        }

        // Efetuo o débito e o crédito
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(request.valor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(request.valor()));

        // Salvo as contas no banco de dados
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        // Retorno o comprovante (Response DTO)
        return new TransacaoResponse(
                "SUCESSO",
                request.valor(),
                LocalDateTime.now(),
                contaOrigem.getIdConta(),
                contaDestino.getIdConta()
        );
    }
}