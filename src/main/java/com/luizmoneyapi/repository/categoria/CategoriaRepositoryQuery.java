package com.luizmoneyapi.repository.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luizmoneyapi.model.Categoria;
import com.luizmoneyapi.repository.filter.CategoriaFilter;

public interface CategoriaRepositoryQuery {
	
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable);

}
