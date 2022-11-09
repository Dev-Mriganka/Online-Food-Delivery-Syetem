package com.healthyswad.service;

import com.healthyswad.exception.ItemException;
import com.healthyswad.model.Item;

public interface ItemService {
	
	public Item addItem(Item item) throws ItemException;
	
	public Item updateItem(Item item) throws ItemException;
	
	public Item viewItem(Item item) throws ItemException;
	
	
	

}
