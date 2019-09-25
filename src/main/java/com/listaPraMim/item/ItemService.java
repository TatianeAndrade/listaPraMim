package com.listaPraMim.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository ir;
	
	public Item criarItem(Item item) {
		ir.save(item);
		return item;
	}
	
	public Item criarItem(String nome) {
		Item item = ir.findByNome(nome);
		
		if( item == null) {
			item = new Item();
			item.setNome(nome);
			ir.save(item);
		}
		
		return item;
	}
	
	public Item getItem(long id) {
		return ir.findById(id).get();
	}
}
