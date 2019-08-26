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
	
	public void criarUsuario(Usuario usuario) {
		us.save(usuario);
	}
	
	public Usuario buscarUsuario(long id) {
		Usuario usuario = us.findById(id).get();
		return usuario;
	}
	
	public Usuario AtualizarUsuario(long id, Usuario usuario) {
		 Usuario user = us.findById(id).get();
		 usuario.setId(user.getId());
		 us.save(usuario);
		 return usuario;
	}
	
	public void removeUsuario(long id) {
		Usuario usuario = us.findById(id).get();
		us.delete(usuario);
	}
}
