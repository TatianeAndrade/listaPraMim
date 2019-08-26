package com.listaPraMim.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.listaPraMim.models.Usuario;
import com.listaPraMim.repositories.UsuarioRepository;
import com.listaPraMim.services.UsuarioService;
import com.listaPraMim.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.USUARIO_URI)
public class UsuarioController {
	
	@Autowired
	private UsuarioService us;
	
	@PostMapping({"/", ""})
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body("Dados Invalidos");
		}
		Usuario user = us.criarUsuario(usuario);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable("id") long id){
		Usuario usuario = us.buscarUsuario(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizaUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario){
		Usuario user = us.AtualizarUsuario(id, usuario);
		return ResponseEntity.ok().body(user);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable("id") long id){
		us.removeUsuario(id);
		return ResponseEntity.ok().build();
	}
}