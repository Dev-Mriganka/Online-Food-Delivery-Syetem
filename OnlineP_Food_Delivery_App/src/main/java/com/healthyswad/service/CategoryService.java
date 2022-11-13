package com.healthyswad.service;

import java.util.Set;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Category;

public interface CategoryService {
	
  public Category addCategory(Category category, String key) throws CategoryException, RestaurantException;
  
  public Category updateCategory(Category category, String key) throws CategoryException, RestaurantException;
  
  public String removeCategory(Integer categoryId, String key) throws CategoryException, RestaurantException;
  
  public Category viewCategory(Integer categoryId, String key) throws CategoryException, RestaurantException;
  
  public Set<Category> viewAllCategoryByRestaurant(String key) throws CategoryException, RestaurantException;
  
  public Category viewCategoryByCustomer(Integer categoryId) throws CategoryException, RestaurantException;
  
  public Set<Category> viewAllCategoryByCustomer(Integer restaurantId) throws CategoryException, RestaurantException;

}
