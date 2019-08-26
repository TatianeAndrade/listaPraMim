package com.listaPraMim.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listaPraMim.models.Usuario;
import com.listaPraMim.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository us;
	
	public Usuario criarUsuario(Usuario usuario) {
		us.save(usuario);
		return usuario;
	}
	
	public Usuario buscarUsuario(long id) {
		Usuario usuario = us.findById(id).get();
		return usuario;
	}
	
	public Usuario atualizarUsuario(long id, Usuario usuario) {
		 Usuario user = us.findById(id).get();
		 user.setEmail(usuario.getEmail());
		 user.setNome(usuario.getNome());
		 user.setSenha(usuario.getSenha());
		 us.save(user);
		 return user;
	}
	
	public void removeUsuario(long id) {
		Usuario usuario = us.findById(id).get();
		us.delete(usuario);
	}
}
