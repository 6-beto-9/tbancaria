package com.java360.tbancaria.core.PessoaFisica.service;

import com.java360.tbancaria.core.PessoaFisica.dtos.PessoaFisicaRequest;
import com.java360.tbancaria.core.PessoaFisica.dtos.PessoaFisicaResponse;
import com.java360.tbancaria.core.PessoaFisica.entity.PessoaFisica;
import com.java360.tbancaria.core.PessoaFisica.repository.PessoaFisicaRepository;
import lombok.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service // 1. Aviso ao Spring denominando o que de fato é essa classe
public class PessoaFisicaService {

    private final PessoaFisicaRepository pessoaFisicaRepository; // 2. Declaro o Repository para justamente mexer no banco

    public PessoaFisicaResponse cadastrarPessoa(PessoaFisicaRequest request) { // 3. O metodo principal que recebe o request e devolve o response

        PessoaFisica novaPessoa = new PessoaFisica(); // Primeiro crio uma entidade vazia para o banco entender

        novaPessoa.setNome(request.nome());
        novaPessoa.setEmail(request.email());
        novaPessoa.setCpf(request.cpf()); // Tiro as informações de dentro do request e jogo na entidade que antes estava vazia



        PessoaFisica pessoaSalva = pessoaFisicaRepository.save(novaPessoa); // Mando o Repository salvar no banco de dados e ele vai devolver com o ID de criação automatica do banco


        return new PessoaFisicaResponse( // Pego os dados que mandei pro banco + o id que ele gerou e junto no DTO de Response para mandar de volta pro app
                pessoaSalva.getId_pessoa(), // ID gerado pelo banco de dados
                pessoaSalva.getNome(),
                pessoaSalva.getCpf(),
                pessoaSalva.getEmail()
        );
    }

    public PessoaFisicaResponse atualizarPessoa(Long id, PessoaFisicaRequest request) {
        PessoaFisica pessoaExistente = pessoaFisicaRepository.findById(id) // Primeiro busco a pessoa no banco pelo ID para garantir que ela existe
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para atualizar.")); // Caso não seja encontrado, o bichinho devolve essa mensagem

        pessoaExistente.setNome(request.nome());  // Atualizo os dados dela com o que veio da tela (Request)
        pessoaExistente.setEmail(request.email());

        PessoaFisica pessoaAtualizada = pessoaFisicaRepository.save(pessoaExistente); // Salvo, como o objeto 'pessoaExistente' já tem ID, o JPA faz um UPDATE automático no banco

        return new PessoaFisicaResponse(
                pessoaAtualizada.getId_pessoa(),
                pessoaAtualizada.getNome(),
                pessoaAtualizada.getCpf(),
                pessoaAtualizada.getEmail()
        ); // Devolvo o Response atualizado

    }

        public void deletarPessoa(Long id) {
            if (!pessoaFisicaRepository.existsById(id)) { // Verifico se o ID realmente existe no banco antes de tentar apagar
                throw new RuntimeException("Não foi possível deletar: Cliente não encontrado.");
            }

            // Mando o repository apagar direto pelo ID (Gera um DELETE FROM no banco)
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
        ); // converte para o DTO de resposta
    }
}