package com.healthyswad.service;

import java.util.List;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.model.Category;

public interface CategoryService {
	
  public Category addCategory(Category category) throws CategoryException;
  public Category updateCategory(Category category) throws CategoryException;
  public Category removeCategory(Category category) throws CategoryException;
  public Category viewCategory(Category category) throws CategoryException;
  public List<Category> viewAllCategory() throws CategoryException;

}
