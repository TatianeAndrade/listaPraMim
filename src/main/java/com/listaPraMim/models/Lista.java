package com.listaPraMim.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Lista implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@OneToMany
	private List<ItemDaLista> itens;
	
	public void cadastrarItem(ItemDaLista item) {
		itens.add(item);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ItemDaLista> getItens() {
		return itens;
	}

	public void setItens(List<ItemDaLista> itens) {
		this.itens = itens;
	}

	public long getId() {
		return id;
	}
}