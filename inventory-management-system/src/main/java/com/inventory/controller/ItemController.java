package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.exception.ItemAlreadyExistsException;
import com.inventory.exception.ItemNotFoundException;
import com.inventory.model.Item;
import com.inventory.service.ItemService;

@RestController
@RequestMapping("/app/item")
public class ItemController {

	@Autowired
    private ItemService itemService;
	
	@PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item createdItem = itemService.createItem(item);
            return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        } catch (ItemAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	
	@PutMapping("/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        try {
            Item updatedItem = itemService.updateItem(itemId, item).orElseThrow(() -> new ItemNotFoundException("Item with ID " + itemId + " not found."));
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        try {
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping
    public ResponseEntity<Void> deleteAllItems() {
        itemService.deleteAllItems();
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable Long itemId) {
        try {
            Item item = itemService.getItemById(itemId).orElseThrow(() -> new ItemNotFoundException("Item with ID " + itemId + " not found."));
            return new ResponseEntity<>(item, HttpStatus.OK);
        } catch (ItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
	
	@GetMapping(params = {"pageSize", "page", "sortBy"})
    public ResponseEntity<List<Item>> getItemsByPage(@RequestParam int pageSize,
                                                     @RequestParam int page,
                                                     @RequestParam String sortBy) {
        return new ResponseEntity<>(itemService.getItemsByPage(pageSize, page, sortBy), HttpStatus.OK);
    }
	
	
}
