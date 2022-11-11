package com.healthyswad.service;

import com.healthyswad.exception.CartException;

import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.FoodCart;
import com.healthyswad.model.Item;

public interface CartService {

	public FoodCart addItemToCart(Item item, String key) throws RestaurantException, ItemException;

	public FoodCart addCart(String key);

	public FoodCart increaseQuantity(Item item, Integer quantity, String key) throws RestaurantException, ItemException;

	public FoodCart reduceQuantity(Item item, Integer quantity, String key) throws ItemException, CartException;

	public FoodCart removeItem(Item item, String key) throws ItemException, CartException;

	public FoodCart clearCart(String key) throws CartException;
}
