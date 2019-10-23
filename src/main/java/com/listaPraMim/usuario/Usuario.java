package com.listaPraMim.usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.listaPraMim.enums.Perfil;
import com.listaPraMim.lista.Lista;

@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@NotEmpty
	@Column(unique=true)
	private String email;
	
	@NotNull
	@NotEmpty
	private String senha;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Lista> listas;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	public Usuario() {
		addPerfil(Perfil.USUARIO);
		addPerfil(Perfil.ADMIN);
	}
	
	public Usuario(long id,  String nome, String email, String senha, List<Lista> listas) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.listas = listas;
		addPerfil(Perfil.USUARIO);
		addPerfil(Perfil.ADMIN);
	}
	
	public long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Lista> getListas() {
		return listas;
	}
	
	public void setListas(List<Lista> listas) {
		this.listas = listas;
	}
	
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	public boolean addLista(Lista lista) {
		return listas.add(lista);
	}
	
	public boolean removeLista(Lista lista) {
		return listas.remove(lista);
	}
}