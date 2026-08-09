package com.java360.tbancaria.core.Conta.service;

import com.java360.tbancaria.core.Conta.dtos.ContaAberturaRequest;
import com.java360.tbancaria.core.Conta.dtos.ContaResponse;
import com.java360.tbancaria.core.Conta.entity.ContaCorrente;
import com.java360.tbancaria.core.Conta.entity.ContaPoupanca;
import com.java360.tbancaria.core.Conta.model.Conta;
import com.java360.tbancaria.core.PessoaFisica.entity.PessoaFisica;
import com.java360.tbancaria.core.Conta.repository.ContaRepository;
import com.java360.tbancaria.core.PessoaFisica.repository.PessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final PessoaFisicaRepository pessoaRepository;

    public ContaResponse abrirConta(ContaAberturaRequest request) {



        PessoaFisica titular = pessoaRepository.findById(request.idTitular())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado. Não é possível abrir conta sem um titular válido."));


        Conta contaSalva;


        if (request.tipoConta().equalsIgnoreCase("POUPANCA")) {


            ContaPoupanca poupanca = new ContaPoupanca();
            poupanca.setTitular(titular);
            poupanca.setSaldo(request.saldoInicial());
            poupanca.setSalarioPoupanca(request.salarioPoupanca());
            poupanca.setTaxaRendimento(new BigDecimal("0.005"));

            contaSalva = contaRepository.save(poupanca);

        } else {

            ContaCorrente corrente = new ContaCorrente();
            corrente.setTitular(titular);
            corrente.setSaldo(request.saldoInicial());
            corrente.setLimiteConta(new BigDecimal("1000.00"));
            corrente.setTarifaMensal(new BigDecimal("10.00"));


            contaSalva = contaRepository.save(corrente);
        }

        return new ContaResponse(
                contaSalva.getIdConta(),
                titular.getNome(),
                request.tipoConta().toUpperCase(),
                contaSalva.getSaldo()
        );
    }

    public ContaResponse buscarPorId(Long id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));

        return new ContaResponse(
                conta.getIdConta(),
                conta.getTitular().getNome(),
                conta.getTipoConta(),
                conta.getSaldo()
        );
    }

    public ContaResponse atualizarSaldo(Long id, BigDecimal novoSaldo) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));

        conta.setSaldo(novoSaldo);
        Conta contaAtualizada = contaRepository.save(conta);

        return new ContaResponse(
                contaAtualizada.getIdConta(),
                contaAtualizada.getTitular().getNome(),
                contaAtualizada.getTipoConta(),
                contaAtualizada.getSaldo()
        );
    }

    public void deletarPorId(Long id) {
        if (!contaRepository.existsById(id)) {
            throw new RuntimeException("Conta não encontrada com o ID: " + id);
        }
        contaRepository.deleteById(id);
    }
}