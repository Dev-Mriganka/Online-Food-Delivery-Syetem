package com.healthyswad.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthyswad.dto.CustomerResDTO;
import com.healthyswad.dto.ItemDTO;
import com.healthyswad.dto.OrderDTO;
import com.healthyswad.dto.RestOrderDto;
import com.healthyswad.dto.RestaurantAddDTO;
import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.ItemException;
import com.healthyswad.exception.OrderDetailsException;
import com.healthyswad.exception.RestaurantException;
import com.healthyswad.model.Category;
import com.healthyswad.model.Item;
import com.healthyswad.model.Restaurant;
import com.healthyswad.service.CategoryService;
import com.healthyswad.service.CustomerService;
import com.healthyswad.service.ItemService;
import com.healthyswad.service.OrderDetailsService;
import com.healthyswad.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService rs;
	
	@Autowired
	private CustomerService cs;
	
	@Autowired
	private ItemService its;
	
	@Autowired
	private OrderDetailsService ods;
	
	@Autowired
	private CategoryService cates;
	
	
	//--------Basic Restaurant operation----------//
	@PostMapping("/create")
	public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody RestaurantAddDTO resDto) throws RestaurantException{
		
		Restaurant rest = rs.addRestaurant(resDto);
		
		return new ResponseEntity<Restaurant>(rest, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Restaurant> updateRestaurant(@Valid @RequestBody RestaurantAddDTO res, @RequestParam String key) throws RestaurantException{
		Restaurant rest =rs.updateRestaurant(res, key);
		
		return new ResponseEntity<Restaurant>(rest,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<Restaurant> removeRestaurant(@RequestParam Integer resId, @RequestParam String key) throws RestaurantException{
		 Restaurant rest = rs.removeRestaurant(resId, key);
		
		return new ResponseEntity<Restaurant>(rest,HttpStatus.OK);
	}
	
	
	
	//--------Restaurant Customer operation----------//
	@GetMapping("/view/customer")
	public ResponseEntity<List<CustomerResDTO>>  viewAllCustomersInRestaurant(@RequestParam Integer restId,@RequestParam String key)throws RestaurantException, CustomerException{
		
		List<CustomerResDTO> list = cs.viewAllCustomersInRestaurant(restId, key);
		
		return new ResponseEntity<List<CustomerResDTO>>(list,HttpStatus.OK);
	}
	
	
	//--------Item operation----------//
	@PostMapping("/item/add")
	public ResponseEntity<Item> addItemHandller(@Valid @RequestBody ItemDTO item, @RequestParam String key) throws ItemException, RestaurantException{
	
       	Item itm = its.addItem(item, key);
		
		return new ResponseEntity<Item>(itm, HttpStatus.OK);
		
	}
		
		
	@PutMapping("/item/update")
	public ResponseEntity<Item> updateItemHandller(@Valid @RequestBody ItemDTO item, @RequestParam String key) throws ItemException, RestaurantException{
			
		Item it = its.updateItem(item, key);
		
			
		return new ResponseEntity<Item>(it, HttpStatus.OK);
			
	}
	
	@DeleteMapping("/item/delete")
	public ResponseEntity<Item> removeItemHandller(@RequestParam Integer itemId, @RequestParam String key) throws ItemException, RestaurantException{
			
		Item it = its.removeItem(itemId, key);
			
		return new ResponseEntity<Item>(it, HttpStatus.OK);
	}
	
	
	@PutMapping("/item/add/category")
	public ResponseEntity<Item> addItemToCategoryByNameHandler(@RequestParam Integer itemId, @Pattern(regexp="^[A-Z][a-z]*") @RequestParam String categoryName, @RequestParam String key) throws ItemException, RestaurantException, CategoryException{
			
		Item it = its.addItemToCategoryByName(itemId, categoryName, key);
		
		return new ResponseEntity<Item>(it, HttpStatus.OK);
			
	}
	
	
	//--------Order operation----------//
	@PutMapping("/order/update")
	public ResponseEntity<OrderDTO> updateStatusHandler(@RequestParam Integer orderId, @RequestParam String key) throws OrderDetailsException, RestaurantException{
		
		OrderDTO oDto = ods.updateStatus(orderId, key);
		
		return new ResponseEntity<OrderDTO>(oDto, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/order/viewall") // --tested--
	public ResponseEntity<List<RestOrderDto>> viewAllOrdersRestaurantHandler(@RequestParam String key)throws OrderDetailsException, CustomerException, RestaurantException{
		
		List<RestOrderDto> dt = ods.viewAllOrdersRestaurant(key);
		
		return new ResponseEntity<List<RestOrderDto>>(dt, HttpStatus.OK);
	}
	
	
	
	//--------Category operation----------//
	@PostMapping("/category/add")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category, String key) throws CategoryException, RestaurantException {
		
		 return new ResponseEntity<Category>(cates.addCategory(category, key),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/category/update")
	public ResponseEntity<Category> updateCategoryHandler(@RequestBody Category category, String key) throws CategoryException, RestaurantException{
		
		 return new ResponseEntity<Category>(cates.updateCategory(category, key),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/category/remove")
	public ResponseEntity<String> deleteCategoryHandler(Integer categoryId, String key) throws CategoryException, RestaurantException{
		
		 return new ResponseEntity<String>(cates.removeCategory(categoryId, key),HttpStatus.OK);
	}
	

	@GetMapping("/category/view")
	public ResponseEntity<Category> viewCategoryHandler(Integer categoryId, String key) throws CategoryException, RestaurantException{
		
		 return new ResponseEntity<Category>(cates.viewCategory(categoryId, key),HttpStatus.OK);
	}
	
	@GetMapping("/category/all")
	public ResponseEntity<Set<Category>> viewAllCategoryByRestaurant(String key) throws CategoryException, RestaurantException{
		  
		return new ResponseEntity<Set<Category>>(cates.viewAllCategoryByRestaurant(key), HttpStatus.ACCEPTED);
		
	}
}
