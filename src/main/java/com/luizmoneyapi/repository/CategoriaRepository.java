package com.luizmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmoneyapi.model.Categoria;
import com.luizmoneyapi.repository.categoria.CategoriaRepositoryQuery;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>, CategoriaRepositoryQuery {

}
