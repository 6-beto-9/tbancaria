package com.java360.tbancaria.core.Transacao.service;

import com.java360.tbancaria.core.Transacao.dtos.TransacaoRequest;
import com.java360.tbancaria.core.Transacao.dtos.TransacaoResponse;
import com.java360.tbancaria.core.Transacao.entity.Transacao;
import com.java360.tbancaria.core.Transacao.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoResponse transferirValor(TransacaoRequest request){

        Transacao novaTransacao = new Transacao();

        novaTransacao.setId(request.idContaOrigem());
        novaTransacao.setId(request.idContaDestinatario());
        novaTransacao.setValor(request.valor());

        Transacao transacaoSalva = transacaoRepository.save(novaTransacao);

        return new TransacaoResponse(
                transacaoSalva.getId(),
                transacaoSalva.getContaOrigem(),
                transacaoSalva.getContaDestinatario(),
                transacaoSalva.getValor(),
                transacaoSalva.getDataHora()
        );
    }
}
