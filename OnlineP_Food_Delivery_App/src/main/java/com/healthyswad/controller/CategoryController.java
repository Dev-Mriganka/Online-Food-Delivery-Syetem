package com.healthyswad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.model.Category;
import com.healthyswad.service.CategoryService;

@RestController
public class CategoryController {

//	@Autowired
//	private CategoryService categoryService;
//	
//	@PostMapping("/categories")
//	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category) throws CategoryException {
//		
//		 return new ResponseEntity<Category>(categoryService.addCategory(category),HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/categories")
//	public ResponseEntity<Category> updateCategoryHandler(@RequestBody Category category) throws CategoryException {
//		
//		 return new ResponseEntity<Category>(categoryService.updateCategory(category),HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/categories")
//	public ResponseEntity<Category> deleteCategoryHandler(@RequestBody Category category) throws CategoryException {
//		
//		 return new ResponseEntity<Category>(categoryService.removeCategory(category),HttpStatus.OK);
//	}
//	
//	@GetMapping("/categories")
//	public ResponseEntity<Category> viewCategoryHandler(@RequestBody Category category) throws CategoryException {
//		
//		 return new ResponseEntity<Category>(categoryService.viewCategory(category),HttpStatus.OK);
//	}
//	
//	@GetMapping("/categories")
//	public ResponseEntity<List<Category>> viewAllCategoryHandler() throws CategoryException {
//		
//		 return new ResponseEntity<List<Category>>(categoryService.viewAllCategory(),HttpStatus.OK);
//	}
//	
	
	
	
	
	
}
