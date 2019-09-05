package com.listaPraMim.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listaPraMim.models.Item;
import com.listaPraMim.models.ItemDaLista;
import com.listaPraMim.models.Lista;
import com.listaPraMim.models.Usuario;
import com.listaPraMim.repositories.ItemDaListaRepository;
import com.listaPraMim.repositories.ListaRepository;

@Service
public class ListaService {

	@Autowired
	private ListaRepository lr;
	
	@Autowired
	private UsuarioService us;
	
	@Autowired
	private ItemService is;
	
	@Autowired
	private ItemDaListaRepository ilr;
	
	public Lista cadastrarLista(Lista lista, long id) {
		Usuario usuario = us.buscarUsuario(id);
		usuario.addLista(lista);
		lr.save(lista);
		return lista;
	}
	
	public void cadastrarItem(String nome, int qtd, long id) {
		Item item = is.criarItem(nome);
		ItemDaLista itemLista = new ItemDaLista();
		itemLista.setQuantidade(qtd);
		Lista lista = lr.findById(id).get();
		itemLista.setItem(item);
		itemLista.setLista(lista);
		lista.cadastrarItem(itemLista);
		ilr.save(itemLista);
		lr.save(lista);
	}
	
	public Lista buscarLista(long id) {
		Lista lista = lr.findById(id).get();
		return lista;
	}
	
	public void removerLista(long id, long idus) {
		Lista lista = lr.findById(id).get();
		Usuario usuario = us.buscarUsuario(idus);
		usuario.removeLista(lista);
		lr.delete(lista);
	}
	
	public Lista atualizarLista(long id, Lista newLista) {
		Lista lista = lr.findById(id).get();
		lista.setNome(newLista.getNome());
		lista.setItens(newLista.getItens());
		lr.save(lista);
		return lista;
	}
	
	public Lista gerarListaAutomatica(long id) {
		int qtd = (int) Math.floor(lr.count() / 2);
		List<Long> lista = lr.itensFrequentes(id, qtd);
		Lista newLista = new Lista();
		newLista.setNome("Lista Automatica");
		newLista = this.cadastrarLista(newLista, id);
		for (Long elemento : lista) {
			Item item = is.getItem(elemento);
			this.cadastrarItem(item.getNome(), 1, newLista.getId());
		}
		return newLista;
	}
}