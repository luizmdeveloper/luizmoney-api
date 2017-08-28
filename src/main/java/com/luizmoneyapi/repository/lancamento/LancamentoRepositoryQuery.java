package com.luizmoneyapi.repository.lancamento;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.luizmoneyapi.model.Lancamento;
import com.luizmoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}
