package com.healthyswad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthyswad.model.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{

}
