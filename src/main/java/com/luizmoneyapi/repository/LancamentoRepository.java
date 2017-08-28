package com.luizmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmoneyapi.model.Lancamento;
import com.luizmoneyapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

}
