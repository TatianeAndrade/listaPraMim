package com.listaPraMim.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listaPraMim.models.Lista;
import com.listaPraMim.repositories.ListaRepository;

@Service
public class ListaService {

	@Autowired
	private ListaRepository lr;
	
	public void cadastrarLista(Lista lista) {
		lr.save(lista);
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
