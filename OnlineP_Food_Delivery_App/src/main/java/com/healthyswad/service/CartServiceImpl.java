package com.healthyswad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.model.FoodCart;
import com.healthyswad.repository.FoodCartDao;


@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private FoodCartDao foodCartDao;
	
	@Autowired
	private ItemDao itemDao;
	
	
	
	@Override
	public FoodCart addItemToCart(Integer itemId, String key) throws CustomerException {
	
		
		return null;
	}

}
