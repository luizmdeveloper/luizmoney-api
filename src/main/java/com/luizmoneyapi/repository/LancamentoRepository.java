package com.luizmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmoneyapi.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
