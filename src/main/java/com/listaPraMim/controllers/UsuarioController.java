package com.listaPraMim.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.listaPraMim.models.Usuario;
import com.listaPraMim.repositories.UsuarioRepository;
import com.listaPraMim.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.USUARIO_URI)
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository us;
	
	@PostMapping({"/", ""})
	public ResponseEntity<?> criarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.ok("faiou");
		}
		us.save(usuario);
		return ResponseEntity.ok(usuario.getNome());
	}
	
	@GetMapping("")
	public ResponseEntity<?> getUsuario(){
		return ResponseEntity.ok("foi");
	}
}