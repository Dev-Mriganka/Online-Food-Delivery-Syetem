package com.healthyswad.service;


import com.healthyswad.exception.CustomerException;
import com.healthyswad.model.FoodCart;

public interface CartService {

<<<<<<< Updated upstream
	public FoodCart addItemToCart(Integer itemId,String key)throws CustomerException;	
=======
	public FoodCart addItemToCart(FoodCart cart,Item item)throws CartException, ItemException;	

	
	public FoodCart addCart(FoodCart cart);
	
    public FoodCart increaseQuantity(FoodCart cart,Item item,Integer quantity) throws ItemException,CartException;
	
	public FoodCart reduceQuantity(FoodCart cart,Item item,Integer quantity)throws ItemException, CartException;
>>>>>>> Stashed changes

}
