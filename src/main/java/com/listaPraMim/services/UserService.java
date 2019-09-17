package com.listaPraMim.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.listaPraMim.security.UsuarioSpringSecurity;

public class UserService {

	public static UsuarioSpringSecurity authenticated() {
		try {
			return (UsuarioSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
	}
}
