package com.listaPraMim.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public Item criarItem(Item item) {
		itemRepository.save(item);
		return item;
	}
	
	public Item criarItem(String nome) {
		Item item = itemRepository.findByNome(nome);
		
		if( item == null) {
			item = new Item();
			item.setNome(nome);
			itemRepository.save(item);
		}
		
		return item;
	}
	
	public Item getItem(long id) {
		return itemRepository.findById(id).get();
	}
}
