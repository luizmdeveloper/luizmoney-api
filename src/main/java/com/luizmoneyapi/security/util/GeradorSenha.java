package com.luizmoneyapi.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
	
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}

}
