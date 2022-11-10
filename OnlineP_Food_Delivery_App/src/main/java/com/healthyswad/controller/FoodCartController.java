package com.healthyswad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthyswad.exception.CartException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.model.FoodCart;
import com.healthyswad.model.Item;
import com.healthyswad.service.CartService;



@RestController
@RequestMapping("/customer")
public class FoodCartController {

	@Autowired
	private CartService cser;
	
	

	@PostMapping("/addItem")
	public  ResponseEntity<FoodCart> addItemToCartHandler(@RequestBody FoodCart cart, @RequestBody Item item) throws CartException, ItemException  {	
		
		FoodCart fd=cser.addItemToCart(cart, item);
		
		return new ResponseEntity<FoodCart>(fd,HttpStatus.OK)  ;

	}
	

	
}
