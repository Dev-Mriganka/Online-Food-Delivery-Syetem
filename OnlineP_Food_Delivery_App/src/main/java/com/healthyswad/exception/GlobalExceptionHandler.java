package com.healthyswad.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<MyErrorDetails> CategoryExceptionHandler(CategoryException ce,WebRequest req) {
			
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ItemException.class)
	public ResponseEntity<MyErrorDetails> ItemExceptionHandler(ItemException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(AddressException.class)
	public ResponseEntity<MyErrorDetails> AddressExceptionHandler(AddressException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> CartExceptionHandler(CartException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(BillException.class)
	public ResponseEntity<MyErrorDetails> BillExceptionHandler(BillException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> CustomerExceptionHandler(CustomerException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(OrderDetailsException.class)
	public ResponseEntity<MyErrorDetails> OrderDetailsExceptionHandler(OrderDetailsException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(RestaurantException.class)
	public ResponseEntity<MyErrorDetails> RestaurantExceptionHandler(RestaurantException ce, WebRequest req){
		
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception ce,WebRequest req) {
			
		MyErrorDetails me = new MyErrorDetails();
		me.setTimestamp(LocalDateTime.now());
		me.setMessage(ce.getMessage());
		me.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(me,HttpStatus.BAD_REQUEST);
		
		
	}

}
