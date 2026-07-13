package com.java360.tbancaria.core.PessoaFisica.repository;

import com.java360.tbancaria.core.PessoaFisica.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
}
