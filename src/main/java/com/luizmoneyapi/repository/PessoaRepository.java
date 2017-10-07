package com.luizmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmoneyapi.model.Pessoa;
import com.luizmoneyapi.repository.pessoa.PessoaRepositoryQuery;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery{

}
 