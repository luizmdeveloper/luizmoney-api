package com.luizmoneyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmoneyapi.model.Lancamento;
import com.luizmoneyapi.model.Pessoa;
import com.luizmoneyapi.repository.LancamentoRepository;
import com.luizmoneyapi.repository.PessoaRepository;
import com.luizmoneyapi.service.exception.PessoaInexistenOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public Lancamento salvar(Lancamento lancamento) {
		buscarPessoaPorCodigo(lancamento);
		
		return lancamentoRepository.save(lancamento);
	}


	private void buscarPessoaPorCodigo(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenOuInativaException();
		}
	}


	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoPorCodigo(lancamento.getCodigo());

		buscarPessoaPorCodigo(lancamentoSalvo);
		
		return lancamentoSalvo;
	}
	
	public Lancamento buscarLancamentoPorCodigo(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);

		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamentoSalvo;
	}

}
