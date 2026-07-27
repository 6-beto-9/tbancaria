package com.java360.tbancaria.core.Transacao.service;

import com.java360.tbancaria.core.Conta.model.Conta;
import com.java360.tbancaria.core.Conta.repository.ContaRepository;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoRequest;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoResponse;
import com.java360.tbancaria.core.Transacao.entity.Transacao;
import com.java360.tbancaria.core.Transacao.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;

    @Transactional // Caso alguma linhe dê erro, tudo é revertido no banco com um rollback
    public TransacaoResponse transferirValor(TransacaoRequest request){

        // Vou buscar a conta de origem
        Conta contaOrigem = contaRepository.findById(request.idContaOrigem())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));

        // Vou buscar a conta de destino
        Conta contaDestino = contaRepository.findById(request.idContaDestino())
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada."));

        // Antes de afazer a transação, verifico se de fato a conta origem tem saldo disponível (comparando BigDecimals)
        if (contaOrigem.getSaldo().compareTo(request.valor()) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a transação.");
        }

        // Agora executo a transferência
        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(request.valor()));
        contaDestino.setSaldo(contaDestino.getSaldo().add(request.valor()));

        // Salvo as contas após a transferência
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        // Registro o histórico da transação
        Transacao transacao = new Transacao();
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestino(contaDestino);
        transacao.setValor(request.valor());
        transacao.setDataTransacao(LocalDateTime.now());

        Transacao transacaoSalva = transacaoRepository.save(transacao);

        // E por fim, retorno a resposta

        return new TransacaoResponse(
                transacaoSalva.getIdTransacao(),
                transacaoSalva.getValor(),
                transacaoSalva.getDataTransacao(),
                contaOrigem.getIdConta(),
                contaDestino.getIdConta()
        );
    }
}
