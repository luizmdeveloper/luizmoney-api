package com.luizmoneyapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.luizmoneyapi.event.RecursoCriadoEvent;
import com.luizmoneyapi.exceptionhandeler.LuizMoneyExceptionHandeler.Erro;
import com.luizmoneyapi.model.Lancamento;
import com.luizmoneyapi.repository.LancamentoRepository;
import com.luizmoneyapi.service.LancamentoService;
import com.luizmoneyapi.service.exception.PessoaInexistenOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping()
	public List<Lancamento> buscar(){
		return lancamentoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lancamento> buscarPorCodigo(@PathVariable Long codigo){
		Lancamento lancamento = lancamentoRepository.findOne(codigo);
		return lancamento != null ? ResponseEntity.ok().body(lancamento) :  ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> gravar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
	}
	
	@ExceptionHandler({PessoaInexistenOuInativaException.class})
	public ResponseEntity<Object> handlePessoaInexistenOuInativaException(PessoaInexistenOuInativaException ex, WebRequest request){
		String mensagemUsuario = messageSource.getMessage("mensagem.pessoa-inexistente-ou-inativo", null, request.getLocale());
		String mensagemDesenvolvedor =  ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		return ResponseEntity.badRequest().body(erros);
	}
}
