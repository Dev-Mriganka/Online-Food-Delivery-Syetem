package com.healthyswad.service;


import com.healthyswad.exception.CustomerException;

public interface CartService {

	public FoodCart addItemToCart(Integer itemId,String key)throws CustomerException;	

}
