package com.healthyswad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{
	
	public List<Address> findByCity(String city);
	
}
