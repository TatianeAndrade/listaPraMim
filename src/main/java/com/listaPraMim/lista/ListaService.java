package com.listaPraMim.lista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listaPraMim.item.Item;
import com.listaPraMim.item.ItemDaLista;
import com.listaPraMim.item.ItemDaListaRepository;
import com.listaPraMim.item.ItemService;
import com.listaPraMim.usuario.Usuario;
import com.listaPraMim.usuario.UsuarioService;

@Service
public class ListaService {

	@Autowired
	private ListaRepository listaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemDaListaRepository itemDaListaRepository;
	
	public Lista cadastrarLista(Lista lista, long idUsuario) {
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		usuario.addLista(lista);
		listaRepository.save(lista);
		return lista;
	}
	
	public void cadastrarItem(String nome, int qtd, long idLista) {
		Item item = itemService.criarItem(nome);
		ItemDaLista itemLista = new ItemDaLista();
		itemLista.setQuantidade(qtd);
		Lista lista = listaRepository.findById(idLista).get();
		itemLista.setItem(item);
		itemLista.setLista(lista);
		lista.cadastrarItem(itemLista);
		itemDaListaRepository.save(itemLista);
		listaRepository.save(lista);
	}
	
	public Lista buscarLista(long idLista) {
		Lista lista = listaRepository.findById(idLista).get();
		return lista;
	}
	
	public void removerLista(long idLista, long idUsuario) {
		Lista lista = listaRepository.findById(idLista).get();
		Usuario usuario = usuarioService.buscarUsuario(idUsuario);
		usuario.removeLista(lista);
		listaRepository.delete(lista);
	}
	
	public Lista atualizarLista(long idLista, Lista newLista) {
		Lista lista = listaRepository.findById(idLista).get();
		lista.setNome(newLista.getNome());
		lista.setItens(newLista.getItens());
		listaRepository.save(lista);
		return lista;
	}
	
	public Lista gerarListaAutomatica(long idUsuario) {
		int qtd = (int) Math.floor(listaRepository.count() / 2);
		List<Long> lista = listaRepository.itensFrequentes(idUsuario, qtd);
		Lista newLista = new Lista();
		newLista.setNome("Lista Automatica");
		newLista = this.cadastrarLista(newLista, idUsuario);
		for (Long elemento : lista) {
			Item item = itemService.getItem(elemento);
			this.cadastrarItem(item.getNome(), 1, newLista.getId());
		}
		return newLista;
	}
}