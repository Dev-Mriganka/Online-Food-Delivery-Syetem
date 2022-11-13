package com.healthyswad.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.healthyswad.model.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{
	
	@Query("from Bill where billDate>=?1 and billDate<=?2")
	public List<Bill> viewAllBillsBetweenDates(LocalDate startDate , LocalDate endDate);

	
}
