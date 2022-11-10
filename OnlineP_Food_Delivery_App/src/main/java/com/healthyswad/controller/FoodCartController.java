package com.healthyswad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.model.FoodCart;
import com.healthyswad.service.CartService;



@RestController
public class FoodCartController {

	@Autowired
	private CartService cser;
	
	

		@PostMapping("/cardinsert")
	public  ResponseEntity<FoodCart> addItemHandler(@RequestParam Integer itemId, @RequestParam(required = false) Integer key ) throws CustomerException,  ItemException {	
		
		
	FoodCart fd=cser.addItemToCart(itemId, key);
		
		
		return new ResponseEntity<FoodCart>(fd,HttpStatus.OK)  ;

		
	}
	
	
	
}
