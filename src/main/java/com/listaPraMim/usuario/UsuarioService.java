package com.listaPraMim.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.listaPraMim.enums.Perfil;
import com.listaPraMim.exceptions.AuthorizationException;
import com.listaPraMim.lista.Lista;
import com.listaPraMim.security.UsuarioSpringSecurity;
import com.listaPraMim.services.UserService;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public Usuario criarUsuario(Usuario usuario) {
		Usuario newUsuario = new Usuario();
		newUsuario.setEmail(usuario.getEmail());
		newUsuario.setListas(usuario.getListas());
		newUsuario.setNome(usuario.getNome());
		newUsuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuarioRepository.save(newUsuario);
		return newUsuario;
	}
	
	public Usuario buscarUsuario(Long idUsuario) {
		UsuarioSpringSecurity usuario = UserService.authenticated();
		if(usuario == null || !usuario.hasRole(Perfil.ADMIN) && !idUsuario.equals(usuario.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Usuario user = usuarioRepository.findById(idUsuario).get();
		return user;
	}
	
	public Usuario atualizarUsuario(long idUsuario, Usuario usuario) {
		 Usuario user = usuarioRepository.findById(idUsuario).get();
		 user.setEmail(usuario.getEmail());
		 user.setNome(usuario.getNome());
		 user.setSenha(usuario.getSenha());
		 usuarioRepository.save(user);
		 return user;
	}
	
	public Usuario cadastrarLista(Lista lista, long idUsuario) {
		Usuario usuario = buscarUsuario(idUsuario);
		usuario.addLista(lista);
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public void removeUsuario(long idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		usuarioRepository.delete(usuario);
	}
}