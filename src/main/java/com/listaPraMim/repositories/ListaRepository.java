package com.listaPraMim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.listaPraMim.models.Lista;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long>{

}