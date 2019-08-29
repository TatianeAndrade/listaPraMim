package com.listaPraMim.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.listaPraMim.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
