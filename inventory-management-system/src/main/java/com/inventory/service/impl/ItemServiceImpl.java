package com.inventory.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventory.exception.ItemAlreadyExistsException;
import com.inventory.exception.ItemNotFoundException;
import com.inventory.model.Item;
import com.inventory.model.Item.ItemStatus;
import com.inventory.repository.ItemRepository;
import com.inventory.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
    private ItemRepository itemRepository;

	@Override
	public Item createItem(Item item) {
		if (itemRepository.findById(item.getItemId()).isPresent()) {
            throw new ItemAlreadyExistsException("Item with ID " + item.getItemId() + " already exists.");
        }
        return itemRepository.save(item);
	}

	@Override
	public Optional<Item> updateItem(Long itemId, Item item) {
		return Optional.ofNullable(itemRepository.findById(itemId).map(existingItem -> {
            existingItem.setItemName(item.getItemName());
            existingItem.setItemBuyingPrice(item.getItemBuyingPrice());
            existingItem.setItemSellingPrice(item.getItemSellingPrice());
            existingItem.setItemLastModifiedByUser(item.getItemLastModifiedByUser());
            existingItem.setItemStatus(item.getItemStatus());
            return itemRepository.save(existingItem);
        }).orElseThrow(() -> new ItemNotFoundException("Item with ID " + itemId + " not found.")));
    }

	@Override
	public void deleteItem(Long itemId) {
		if (!itemRepository.findById(itemId).isPresent()) {
            throw new ItemNotFoundException("Item with ID " + itemId + " not found.");
        }
        itemRepository.deleteById(itemId);
	}

	@Override
	public void deleteAllItems() {
		itemRepository.deleteAll();
	}

	@Override
	public Optional<Item> getItemById(Long itemId) {
		return Optional.ofNullable(itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item with ID " + itemId + " not found.")));
    }

	@Override
	public List<Item> getAllItems() {
		 return itemRepository.findAll();
	}

	@Override
	public List<Item> getItemsByStatusAndUser(ItemStatus itemStatus, String itemEnteredByUser) {
		 return itemRepository.findByItemStatusAndItemEnteredByUser(itemStatus, itemEnteredByUser);
    }

	@Override
	public List<Item> getItemsByPage(int pageSize, int page, String sortBy) {
		 Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
	        return itemRepository.findAll(pageable).getContent();
	    }

}
