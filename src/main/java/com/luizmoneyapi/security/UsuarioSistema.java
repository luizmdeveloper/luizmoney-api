package com.luizmoneyapi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.luizmoneyapi.model.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario ususario;
		
	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorites) {
		super(usuario.getEmail(), usuario.getSenha(), authorites);
		this.ususario = usuario;
	}

	public Usuario getUsusario() {
		return ususario;
	}
}
