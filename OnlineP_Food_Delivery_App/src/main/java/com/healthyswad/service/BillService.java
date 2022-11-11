package com.healthyswad.service;

import java.time.LocalDate;
import java.util.List;

import com.healthyswad.exception.BillException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.model.Bill;

public interface BillService {
	
	
	public Bill addBill(Bill bill)throws BillException;
	
	public Bill updateBill(Bill bill)throws BillException;
	
	public Bill removeBill(Bill bill)throws BillException;
	
	public Bill viewBill(Bill bill)throws BillException;
	
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate)throws BillException;
	
	public List<Bill> viewBill(String custId)throws BillException, CustomerException;
	
	public Bill calculateTotalCost(Bill bill)throws BillException;
	

}
