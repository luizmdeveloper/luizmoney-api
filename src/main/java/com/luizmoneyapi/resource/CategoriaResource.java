package com.luizmoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luizmoneyapi.event.RecursoCriadoEvent;
import com.luizmoneyapi.model.Categoria;
import com.luizmoneyapi.repository.CategoriaRepository;
import com.luizmoneyapi.repository.filter.CategoriaFilter;
import com.luizmoneyapi.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public Page<Categoria> buscar(CategoriaFilter categoriaFilter, Pageable pageable){
		return categoriaRepository.filtrar(categoriaFilter, pageable);
	}
	
	@GetMapping("/todos")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public List<Categoria> buscarTodos(){
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
	public ResponseEntity<Categoria> buscarPorCodigo(@PathVariable long codigo){	
		Categoria categoria = categoriaRepository.findOne(codigo);		
		return categoria != null ? ResponseEntity.ok().body(categoria) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Categoria> gravar(@Valid @RequestBody Categoria categoria, HttpServletResponse response){
		Categoria categoriaSalva =  categoriaRepository.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));

		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable long codigo, @Valid @RequestBody Categoria Categoria){
		Categoria categoria = categoriaService.atualizar(codigo, Categoria);
		return ResponseEntity.ok().body(categoria);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable long codigo){
		categoriaRepository.delete(codigo);
	}
}
