package com.healthyswad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthyswad.exception.ItemException;
import com.healthyswad.model.Item;
import com.healthyswad.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	
	
	@Autowired
	private ItemService itemService;
	
	@PostMapping("/addItem")
	public ResponseEntity<Item> addItem(@RequestBody Item item) throws ItemException{
		
		Item it = itemService.addItem(item);
		
		return new ResponseEntity<Item>(it, HttpStatus.OK);
		
		
	}
	
	
	@PutMapping("/updateItem")
	public ResponseEntity<Item> updateItem(@RequestBody Item item) throws ItemException{
		
		
		Item it = itemService.updateItem(item);
		
		return new ResponseEntity<Item>(it, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/viewItem")
	public ResponseEntity<Item> viewItem(@RequestBody Item item) throws ItemException{
		
		Item it = itemService.viewItem(item);
		
		
		return new ResponseEntity<Item>(it, HttpStatus.OK);
	}
	
	
	public ResponseEntity<Item> removeItem(@RequestBody Item item) throws ItemException{
		
		Item it = itemService.removeItem(item);
		
		return new ResponseEntity<Item>(it, HttpStatus.OK);
	}
	
	
	
	

}
