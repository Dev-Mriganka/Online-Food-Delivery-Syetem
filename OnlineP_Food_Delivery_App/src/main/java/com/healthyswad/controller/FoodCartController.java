package com.healthyswad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	

	@PostMapping("/cart/addItem")
	public  ResponseEntity<FoodCart> addItemToCartHandler(@RequestBody FoodCart cart, @RequestBody Item item) throws CartException, ItemException  {	
		
		FoodCart fd=cser.addItemToCart(cart, item);
		
		return new ResponseEntity<FoodCart>(fd,HttpStatus.OK)  ;

	}
	

	    public ResponseEntity<FoodCart> addCart(@RequestBody FoodCart cart){
	    	
	    	
            FoodCart foodCart = cser.addCart(cart);
			
			return new ResponseEntity<FoodCart>(foodCart,HttpStatus.OK);
			
	    	
	    	
	    }
	
	    @PutMapping("/cart/increase")
	   public ResponseEntity<FoodCart> increaseQuantity(@RequestBody FoodCart cart,@RequestBody Item item,@RequestBody Integer quantity) throws ItemException,CartException{
		   
		   
			FoodCart foodCart = cser.increaseQuantity(cart,item,quantity);
			
			return new ResponseEntity<FoodCart>(foodCart,HttpStatus.OK);
		   
		   
		   
	   }
		
	    @PutMapping("/cart/reduce")
		public ResponseEntity<FoodCart> reduceQuantity(@RequestBody FoodCart cart,@RequestBody Item item,@RequestBody Integer quantity)throws ItemException, CartException{
			

			FoodCart foodCart = cser.reduceQuantity(cart,item,quantity);
			
			return new ResponseEntity<FoodCart>(foodCart,HttpStatus.OK);
			
		}

	    @DeleteMapping("/cart/remove")
		public ResponseEntity<FoodCart> removeItem(@RequestBody FoodCart cart,@RequestBody Item item)throws ItemException,CartException{
			
			FoodCart foodCart = cser.removeItem(cart, item);
			
			return new ResponseEntity<FoodCart>(foodCart,HttpStatus.OK);
			
		}
		
		
		
		
	    @DeleteMapping("/cart/clear")
		public ResponseEntity<FoodCart> clearCart(@RequestBody FoodCart cart)throws CartException{
			

			
    	FoodCart foodCart = cser.clearCart(cart);
	
	    return new ResponseEntity<FoodCart>(foodCart,HttpStatus.OK);
			
			
			
		}
		
		
		
		
		
		
}



















