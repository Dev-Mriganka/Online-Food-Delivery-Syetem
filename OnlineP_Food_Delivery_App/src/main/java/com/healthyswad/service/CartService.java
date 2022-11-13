package com.healthyswad.service;

import com.healthyswad.exception.CartException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.FoodCart;

public interface CartService {

	public FoodCart addItemToCart(Integer itemId, String key) throws RestaurantException, ItemException;

	public FoodCart increaseQuantity(Integer itemId, Integer quantity, String key) throws RestaurantException, ItemException, CartException;

	public FoodCart reduceQuantity(Integer itemId, Integer quantity, String key) throws RestaurantException, ItemException, CartException;

	public FoodCart removeItem(Integer itemId, String key) throws RestaurantException, ItemException, CartException;

	public String clearCart(String key) throws RestaurantException, CartException;
	
	public FoodCart viewCart(String key) throws RestaurantException, CartException;
}
