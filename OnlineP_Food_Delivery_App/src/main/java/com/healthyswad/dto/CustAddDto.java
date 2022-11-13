package com.healthyswad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustAddDto {
	
	
	private Integer customerId;
	private String fullName;
	private Integer age;
	private String gender;
	private String mobileNumber;
	private String email;
	private String password;
	
	
}
