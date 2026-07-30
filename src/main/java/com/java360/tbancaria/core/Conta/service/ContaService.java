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
@RequiredArgsConstructor // Lombok injeta os dois Repositories que precisamos usar aqui
public class ContaService {

    // Preciso do repository de contas e também do de pessoas (para buscar o titular)
    private final ContaRepository contaRepository;
    private final PessoaFisicaRepository pessoaRepository;

    public ContaResponse abrirConta(ContaAberturaRequest request) {

        // Busco o cliente no banco de dados para ser o titular da nova conta.
        // Se o cliente não existir no banco, ele para a operação e lança um erro
        PessoaFisica titular = pessoaRepository.findById(request.idTitular())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado. Não é possível abrir conta sem um titular válido."));

        // Crio uma variável genérica do tipo 'Conta' (a classe mãe)
        Conta contaSalva;

        // Comparo o tipo enviado no request (ignorando maiúsculas/minúsculas)
        if (request.tipoConta().equalsIgnoreCase("POUPANCA")) {

            // Se for poupança, instanciamos a classe filha ContaPoupanca
            ContaPoupanca poupanca = new ContaPoupanca();
            poupanca.setTitular(titular);
            poupanca.setSaldo(request.saldoInicial());
            poupanca.setSalarioPoupanca(request.salarioPoupanca()); // Atributo exclusivo de Poupança
            poupanca.setTaxaRendimento(new BigDecimal("0.005")); // Rendimento padrão de 0.5%

            // Salvo no banco usando o repository comum
            contaSalva = contaRepository.save(poupanca);

        } else {

            // Se não for poupança, por padrão instancio a classe filha ContaCorrente
            ContaCorrente corrente = new ContaCorrente();
            corrente.setTitular(titular);
            corrente.setSaldo(request.saldoInicial());
            corrente.setLimiteConta(new BigDecimal("1000.00")); // Limite de crédito padrão
            corrente.setTarifaMensal(new BigDecimal("10.00")); // Tarifa de manutenção padrão

            // Salvo no banco usando o mesmo repository comum
            contaSalva = contaRepository.save(corrente);
        }

        // Monto e retorna o Response com os dados finais consolidados
        return new ContaResponse(
                contaSalva.getIdConta(), // O ID gerado pelo banco para a nova conta
                titular.getNome(),       // O nome do dono que busco
                request.tipoConta().toUpperCase(), // O tipo da conta formatado bonitinho
                contaSalva.getSaldo()    // O saldo inicial da conta
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