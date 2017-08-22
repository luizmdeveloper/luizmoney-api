package com.luizmoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizmoneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
