package com.healthyswad.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthyswad.exception.CategoryException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.exception.OrderDetailsException;
import com.healthyswad.exception.RestaurantExcaption;
import com.healthyswad.model.Category;
import com.healthyswad.model.Customer;
import com.healthyswad.model.OrderDetails;
import com.healthyswad.model.Restaurant;
import com.healthyswad.service.OrderDetailsService;

@RestController
public class OrderDetailsController {
	
	private OrderDetailsService os;
	
	@PostMapping("/addOrderDetails")
	public ResponseEntity<OrderDetails> addOrderDetailHandler(@RequestBody OrderDetails order) throws OrderDetailsException {
		
		 return new ResponseEntity<OrderDetails>(os.addDetails(order),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/updateOrderDetails")
	public ResponseEntity<OrderDetails> updateOrderDetailHandler(@RequestBody OrderDetails order) throws OrderDetailsException {
		
		 return new ResponseEntity<OrderDetails>(os.updateOrder(order),HttpStatus.ACCEPTED);
	}
	

	@DeleteMapping("/delOrderDetails")
	public ResponseEntity<OrderDetails> deleteOrderDetailHandler(@RequestBody OrderDetails order) throws OrderDetailsException {
		
		 return new ResponseEntity<OrderDetails>(os.removeOrder(order),HttpStatus.OK);
	}
	
	@GetMapping("/viewOrderDetails")
	public ResponseEntity<OrderDetails> viewOrderDetailHandler(@RequestBody OrderDetails order) throws OrderDetailsException {
		
		 return new ResponseEntity<OrderDetails>(os.viewOrder(order),HttpStatus.OK);
	}
	
	@GetMapping("/viewallresOrderDetails")
	public ResponseEntity<List<OrderDetails>> viewAllResOrderDetailHandler(@RequestBody Restaurant res) throws OrderDetailsException, RestaurantExcaption {
		
		 return new ResponseEntity<List<OrderDetails>>(os.viewAllOrders(res),HttpStatus.OK);
	}
	
	@GetMapping("/viewallcusOrderDetails")
	public ResponseEntity<List<OrderDetails>> viewAllCusOrderDetailHandler(@RequestBody Customer cus) throws OrderDetailsException, RestaurantExcaption, CustomerException {
		
		 return new ResponseEntity<List<OrderDetails>>(os.viewAllOrders(cus),HttpStatus.OK);
	}
	

}
