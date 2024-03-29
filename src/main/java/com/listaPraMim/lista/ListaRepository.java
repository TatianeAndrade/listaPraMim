package com.listaPraMim.lista;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ListaRepository extends JpaRepository<Lista, Long>{
	@Query(
			value = "SELECT item_id FROM item_da_lista i WHERE i.lista_id IN (SELECT listas_id FROM usuario_listas u WHERE u.usuario_id=?1) GROUP BY item_id HAVING COUNT(item_id) > ?2",
			nativeQuery = true)
		List<Long> itensFrequentes(long idUsuario, int qtd);
	
	@Query(
			value = "SELECT * FROM lista i WHERE i.id IN (SELECT listas_id FROM usuario_listas u WHERE u.usuario_id=?1)",
			nativeQuery = true)
	List<Lista> TodasListaUsuario(Long idUsuario);
}