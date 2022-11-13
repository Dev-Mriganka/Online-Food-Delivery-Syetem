package com.healthyswad.dto;

import java.util.ArrayList;
import java.util.List;

import com.healthyswad.model.Address;
import com.healthyswad.model.OrderDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viewprofile {

	private Integer customerId;
	private String fullName;
	private Integer age;
	private String gender;
	private String mobileNumber;
	private String email;

	private List<Address> addresses = new ArrayList<>();
	
	private List<OrderDetails> orders = new ArrayList<>();
	
}
