package com.listaPraMim.lista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.listaPraMim.item.Item;
import com.listaPraMim.item.ItemDaLista;
import com.listaPraMim.utils.RestConstants;

@RestController
@RequestMapping(RestConstants.LISTA_URI)
public class ListaController {
	
	@Autowired
	private ListaService listaService;
	
	@PostMapping({"/{id}"})
	public ResponseEntity<Object> criarLista(@PathVariable("id") long idUsuario, @Valid @RequestBody Lista lista, BindingResult result){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Dados Invalidos!");
		}
		Lista list = listaService.cadastrarLista(lista, idUsuario);
		HashMap<String, Object> resp = new HashMap<>();
		resp.put("id", list.getId());
		resp.put("nome", list.getNome());
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(resp, responseHeaders, HttpStatus.CREATED);
	}
	
	@PostMapping("/item/{id}")
	public ResponseEntity<?> criarItem(@PathVariable("id") long idLista, @RequestBody HashMap<String, Object> req, BindingResult result){
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Dados Invalidos!");
		}
		listaService.cadastrarItem((String) req.get("nome"), (int) req.get("qtd"), idLista);
		return ResponseEntity.ok(req.get("qtd"));
	}
	
	@DeleteMapping("/{id}&{idus}")
	public ResponseEntity<?> removerLista(@PathVariable("id") long idLista, @PathVariable("idus") long idUsuario){
		listaService.removerLista(idLista, idUsuario);
		return ResponseEntity.ok().build();
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarLista(@PathVariable("id") long idLista){
		Lista lista = listaService.buscarLista(idLista);
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
	public ResponseEntity<?> atualizarLista(@PathVariable("id") long idLista, @Valid @RequestBody Lista lista){
		Lista newLista = listaService.atualizarLista(idLista, lista);
		return ResponseEntity.ok().body(newLista);
	}
	
	@GetMapping("/auto/{id}")
	public ResponseEntity<?> gerarListaAutomatica(@PathVariable("id") long idUsuario){
		Lista lista = listaService.gerarListaAutomatica(idUsuario);
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
	
	@GetMapping("/u/{id}")
	public ResponseEntity<?> getListasUsuario(@PathVariable("id") Long idUsuario) throws InterruptedException{
		List<Lista> result = listaService.buscarListas(idUsuario);
		return ResponseEntity.ok().body(result);
		
	}
	
}