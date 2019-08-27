package com.listaPraMim.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listaPraMim.models.Lista;
import com.listaPraMim.models.Usuario;
import com.listaPraMim.repositories.ListaRepository;

@Service
public class ListaService {

	@Autowired
	private ListaRepository lr;
	
	@Autowired
	private UsuarioService us;
	
	public Lista cadastrarLista(Lista lista, long id) {
		lr.save(lista);
		Usuario usuario = us.cadastrarLista(lista, id);
		lista.setUsuario(usuario);
		lr.save(lista);
		return lista;
	}
	
	public Lista buscarLista(long id) {
		Lista lista = lr.findById(id).get();
		return lista;
	}
	
	public void removerLista(long id) {
		Lista lista = lr.findById(id).get();
		lr.delete(lista);
	}
	
	public Lista atualizarLista(long id, Lista newLista) {
		Lista lista = lr.findById(id).get();
		lista.setNome(newLista.getNome());
		lista.setItens(newLista.getItens());
		lr.save(lista);
		return lista;
	}
}