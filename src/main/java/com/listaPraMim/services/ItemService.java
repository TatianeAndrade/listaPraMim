package com.listaPraMim.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.listaPraMim.models.Item;
import com.listaPraMim.repositories.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository ir;
	
	public Item criarItem(Item item) {
		ir.save(item);
		return item;
	}
	
	public Item criarItem(String nome) {
		Item item = new Item();
		item.setNome(nome);
		ir.save(item);
		return item;
	}
}
