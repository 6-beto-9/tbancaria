package com.java360.tbancaria.core.Conta.repository;

import com.java360.tbancaria.core.Conta.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
