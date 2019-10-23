package com.listaPraMim.usuario;

import java.util.List;
import java.util.ArrayList;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listaPraMim.utils.RestConstants;


@RestController
@RequestMapping(RestConstants.USUARIO_URI)
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping({"/", ""})
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body("Dados Invalidos");
		}
		Usuario user = usuarioService.criarUsuario(usuario);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(user, responseHeaders, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable("id") long idUsuario){
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		return ResponseEntity.ok().body(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizaUsuario(@PathVariable("id") long idUsuario, @RequestBody Usuario usuario){
		Usuario user = usuarioService.atualizarUsuario(idUsuario, usuario);
		return ResponseEntity.ok().body(user);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable("id") long idUsuario){
		usuarioService.removeUsuario(idUsuario);
		return ResponseEntity.ok().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() throws InterruptedException{
		List<Usuario> usuarios = usuarioService.buscarTodosUsuario();
		return ResponseEntity.ok(usuarios);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/s")
	public ResponseEntity<List<Usuario>> buscarTodosUsuariosSemCache() throws InterruptedException{
		List<Usuario> usuarios = usuarioService.buscarTodosUsuarioSemCache();
		return ResponseEntity.ok(usuarios);
	}
	
	
}