package com.luizmoneyapi.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luizmoneyapi.model.Usuario;
import com.luizmoneyapi.repository.UsuarioRepository;

@Service
public class AppUserDetailsServer implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha inválida"));	
		return new UsuarioSistema(usuario, getPermissao(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissao(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getNome().toUpperCase())));
		return authorities;
	}

}
