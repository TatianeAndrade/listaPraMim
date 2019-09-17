package com.listaPraMim.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.listaPraMim.enums.Perfil;
import com.listaPraMim.exceptions.AuthorizationException;
import com.listaPraMim.models.Lista;
import com.listaPraMim.models.Usuario;
import com.listaPraMim.repositories.UsuarioRepository;
import com.listaPraMim.security.UsuarioSpringSecurity;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository us;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario criarUsuario(Usuario usuario) {
		Usuario newUsuario = new Usuario();
		newUsuario.setEmail(usuario.getEmail());
		newUsuario.setListas(usuario.getListas());
		newUsuario.setNome(usuario.getNome());
		newUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		us.save(newUsuario);
		return newUsuario;
	}
	
	public Usuario buscarUsuario(long id) {
		UsuarioSpringSecurity usuario = UserService.authenticated();
		if(usuario == null || !usuario.hasRole(Perfil.ADMIN) && !id.equals(usuario.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario user = us.findById(id).get();
		return user;
	}
	
	public Usuario atualizarUsuario(long id, Usuario usuario) {
		 Usuario user = us.findById(id).get();
		 user.setEmail(usuario.getEmail());
		 user.setNome(usuario.getNome());
		 user.setSenha(usuario.getSenha());
		 us.save(user);
		 return user;
	}
	
	public Usuario cadastrarLista(Lista lista, long id) {
		Usuario usuario = buscarUsuario(id);
		usuario.addLista(lista);
		us.save(usuario);
		return usuario;
	}
	
	public void removeUsuario(long id) {
		Usuario usuario = us.findById(id).get();
		us.delete(usuario);
	}
}