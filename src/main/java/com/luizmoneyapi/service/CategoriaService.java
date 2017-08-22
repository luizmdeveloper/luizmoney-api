package com.luizmoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmoneyapi.model.Categoria;
import com.luizmoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria atualizar(long codigo, Categoria categoria) {
		Categoria categoriaSalva = buscarCategoriaPorCodigo(codigo);		
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		return categoriaRepository.save(categoriaSalva);
	}

	private Categoria buscarCategoriaPorCodigo(long codigo) {
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		
		if (categoriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaSalva;
	}

}
