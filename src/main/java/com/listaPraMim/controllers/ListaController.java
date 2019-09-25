package com.listaPraMim.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.listaPraMim.models.Item;
import com.listaPraMim.models.ItemDaLista;
import com.listaPraMim.models.Lista;
import com.listaPraMim.services.ItemService;
import com.listaPraMim.services.ListaService;
import com.listaPraMim.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.LISTA_URI)
public class ListaController {
	
	@Autowired
	private ListaService ls;
	
	@Autowired
	private ItemService is;
	
	@PostMapping({"/{id}"})
	public ResponseEntity<Object> criarLista(@PathVariable("id") long id, @Valid @RequestBody Lista lista, BindingResult result){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Dados Invalidos!");
		}
		Lista list = ls.cadastrarLista(lista, id);
		HashMap<String, Object> resp = new HashMap<>();
		resp.put("id", list.getId());
		resp.put("nome", list.getNome());
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(resp, responseHeaders, HttpStatus.CREATED);
	}
	
	@PostMapping("/item/{id}")
	public ResponseEntity<?> criarItem(@PathVariable("id") long id, @RequestBody HashMap<String, Object> req, BindingResult result){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Dados Invalidos!");
		}
		ls.cadastrarItem((String) req.get("nome"), (int) req.get("qtd"), id);
		return ResponseEntity.ok(req.get("qtd"));
	}
	
	@DeleteMapping("/{id}&{idus}")
	public ResponseEntity<?> removerLista(@PathVariable("id") long id, @PathVariable("idus") long idus){
		ls.removerLista(id, idus);
		return ResponseEntity.ok().build();
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarLista(@PathVariable("id") long id){
		Lista lista = ls.buscarLista(id);
		HashMap<String, Object> resp = new HashMap<>();
		List<ItemDaLista> itensDaLista = lista.getItens();
		List<Item> itens = new ArrayList<>();
		for (ItemDaLista itemDaLista : itensDaLista) {
			itens.add(itemDaLista.getItem());
		}
		resp.put("id", lista.getId());
		resp.put("nome", lista.getNome());
		resp.put("itens", itens);
		return ResponseEntity.ok().body(resp);
	}
	
	@PutMapping(("/{id}"))
	public ResponseEntity<?> atualizarLista(@PathVariable("id") long id, @Valid @RequestBody Lista lista){
		Lista newLista = ls.atualizarLista(id, lista);
		return ResponseEntity.ok().body(newLista);
	}
	
	@GetMapping("/auto/{id}")
	public ResponseEntity<?> gerarListaAutomatica(@PathVariable("id") long id){
		Lista lista = ls.gerarListaAutomatica(id);
		HashMap<String, Object> resp = new HashMap<>();
		List<ItemDaLista> itensDaLista = lista.getItens();
		List<Item> itens = new ArrayList<>();
		for (ItemDaLista itemDaLista : itensDaLista) {
			itens.add(itemDaLista.getItem());
		}
		resp.put("id", lista.getId());
		resp.put("nome", lista.getNome());
		resp.put("itens", itens);
		return ResponseEntity.ok().body(resp);
		
	}
	
}