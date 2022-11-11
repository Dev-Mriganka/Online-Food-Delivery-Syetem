package com.healthyswad.service;

import java.util.Set;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Category;

public interface CategoryService {
	
  public Category addCategory(Category category, String key) throws CategoryException, RestaurantException;
  
  public Category updateCategory(Category category, String key) throws CategoryException, RestaurantException;
  
  public Category removeCategory(Category category, String key) throws CategoryException, RestaurantException;
  
  public Category viewCategory(Category category, String key) throws CategoryException, RestaurantException;
  
  public Set<Category> viewAllCategory(String key) throws CategoryException, RestaurantException;

}
