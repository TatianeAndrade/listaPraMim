package com.listaPraMim.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.listaPraMim.models.Lista;
import com.listaPraMim.services.ListaService;
import com.listaPraMim.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.LISTA_URI)
public class ListaController {
	
	@Autowired
	private ListaService ls;
	
	@PostMapping({"/", ""})
	public ResponseEntity<?> criarLista(@Valid @RequestBody Lista lista, BindingResult result){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Dados Invalidos!");
		}
		ls.cadastrarLista(lista);
		return ResponseEntity.ok(lista.getNome());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerLista(@PathVariable("id") long id){
		ls.removerLista(id);
		return ResponseEntity.accepted().body("ok");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarLista(@PathVariable("id") long id){
		Lista lista = ls.buscarLista(id);
		return ResponseEntity.accepted().body(lista);
	}
	
	@PutMapping(("/{id}"))
	public ResponseEntity<?> atualizarLista(@PathVariable("id") long id, @Valid @RequestBody Lista lista){
		Lista newLista = ls.atualizarLista(id, lista);
		return ResponseEntity.accepted().body(newLista);
	}
	
}