package com.inventory.service;

import java.util.List;
import java.util.Optional;

import com.inventory.model.Item;

public interface ItemService {

	Item createItem(Item item);
	Optional<Item> updateItem(Long itemId, Item item);
	void deleteItem(Long itemId);
	void deleteAllItems();
	Optional<Item> getItemById(Long itemId);
	List<Item> getAllItems();
	List<Item> getItemsByStatusAndUser(Item.ItemStatus itemStatus, String itemEnteredByUser);
	List<Item> getItemsByPage(int pageSize, int page, String sortBy);
}
