package com.java360.tbancaria.core.Transacao.repository;

import com.java360.tbancaria.core.Transacao.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
