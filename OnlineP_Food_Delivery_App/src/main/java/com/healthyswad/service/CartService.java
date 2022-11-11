package com.healthyswad.service;


import com.healthyswad.exception.CartException;

import com.healthyswad.exception.ItemException;


import com.healthyswad.model.FoodCart;
import com.healthyswad.model.Item;

public interface CartService {

	public FoodCart addItemToCart(FoodCart cart,Item item)throws CartException, ItemException;	

	public FoodCart addCart(FoodCart cart);
	
  public FoodCart increaseQuantity(FoodCart cart,Item item,Integer quantity) throws ItemException,CartException;

	public FoodCart reduceQuantity(FoodCart cart,Item item,Integer quantity)throws ItemException, CartException;

	public FoodCart removeItem(FoodCart cart,Item item)throws ItemException,CartException;
	
	public FoodCart clearCart(FoodCart cart)throws CartException;
}
