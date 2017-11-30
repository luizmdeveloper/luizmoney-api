package com.luizmoneyapi.repository.categoria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.luizmoneyapi.model.Categoria;
import com.luizmoneyapi.model.Categoria_;
import com.luizmoneyapi.repository.filter.CategoriaFilter;

public class CategoriaRepositoryImpl implements CategoriaRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		
		Predicate[] predicates = criarPredicates(categoriaFilter, builder, root);
		
		criteria.where(predicates);
		TypedQuery<Categoria> query = entityManager.createQuery(criteria);
		adicionarRestricoesPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(categoriaFilter)); 
	}
	
	private Predicate[] criarPredicates(CategoriaFilter categoriaFilter, CriteriaBuilder builder, Root<Categoria> root) {
		List<Predicate> predicates = new ArrayList<>();		
		
		if (!StringUtils.isEmpty(categoriaFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Categoria_.nome)) , "%"+categoriaFilter.getNome().toLowerCase()+"%"));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
		
	private void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	
	private Long total(CategoriaFilter categoriaFilter) {		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		
		Predicate[] predicates = criarPredicates(categoriaFilter, builder, root);
		
		criteria.select(builder.count(root));
		criteria.where(predicates);		
		
		return entityManager.createQuery(criteria).getSingleResult(); 
	} 

}
