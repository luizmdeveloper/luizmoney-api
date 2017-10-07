package com.luizmoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.luizmoneyapi.model.Lancamento;
import com.luizmoneyapi.model.Pessoa;
import com.luizmoneyapi.repository.LancamentoRepository;
import com.luizmoneyapi.repository.PessoaRepository;
import com.luizmoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	public Lancamento salvar(Lancamento lancamento) {
		buscarPessoaPorCodigo(lancamento.getPessoa().getCodigo());
		
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoPorCodigo(codigo);
		
		if (!lancamentoSalvo.getPessoa().getCodigo().equals(lancamento.getPessoa().getCodigo())) {
			buscarPessoaPorCodigo(lancamento.getPessoa().getCodigo());
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	public Lancamento buscarLancamentoPorCodigo(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(codigo);

		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return lancamentoSalvo;
	}

	private void buscarPessoaPorCodigo(Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		
		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}	
}
