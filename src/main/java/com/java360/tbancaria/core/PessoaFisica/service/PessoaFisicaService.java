package com.java360.tbancaria.core.PessoaFisica.service;

import com.java360.tbancaria.core.PessoaFisica.dtos.PessoaFisicaRequest;
import com.java360.tbancaria.core.PessoaFisica.dtos.PessoaFisicaResponse;
import com.java360.tbancaria.core.PessoaFisica.entity.PessoaFisica;
import com.java360.tbancaria.core.PessoaFisica.repository.PessoaFisicaRepository;
import lombok.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class PessoaFisicaService {

    private final PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisicaResponse cadastrarPessoa(PessoaFisicaRequest request) {

        PessoaFisica novaPessoa = new PessoaFisica();

        novaPessoa.setNome(request.nome());
        novaPessoa.setEmail(request.email());
        novaPessoa.setCpf(request.cpf());



        PessoaFisica pessoaSalva = pessoaFisicaRepository.save(novaPessoa);


        return new PessoaFisicaResponse(
                pessoaSalva.getId_pessoa(),
                pessoaSalva.getNome(),
                pessoaSalva.getCpf(),
                pessoaSalva.getEmail()
        );
    }

    public PessoaFisicaResponse atualizarPessoa(Long id, PessoaFisicaRequest request) {
        PessoaFisica pessoaExistente = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para atualizar."));

        pessoaExistente.setNome(request.nome());
        pessoaExistente.setEmail(request.email());

        PessoaFisica pessoaAtualizada = pessoaFisicaRepository.save(pessoaExistente);

        return new PessoaFisicaResponse(
                pessoaAtualizada.getId_pessoa(),
                pessoaAtualizada.getNome(),
                pessoaAtualizada.getCpf(),
                pessoaAtualizada.getEmail()
        );

    }

        public void deletarPessoa(Long id) {
            if (!pessoaFisicaRepository.existsById(id)) {
                throw new RuntimeException("Não foi possível deletar: Cliente não encontrado.");
            }

            pessoaFisicaRepository.deleteById(id);
        }

    public PessoaFisicaResponse buscarPorId(Long id) {
        PessoaFisica pessoa = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new PessoaFisicaResponse(
                pessoa.getId_pessoa(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEmail()
        );
    }
}