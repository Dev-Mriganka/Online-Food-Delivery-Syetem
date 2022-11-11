package com.healthyswad.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.healthyswad.exception.BillException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.model.Bill;

@Service
public class BillServiceImpl implements BillService{

	@Override
	public Bill addBill(Bill bill) throws BillException {
		
		return null;
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		
		return null;
	}

	@Override
	public Bill removeBill(Bill bill) throws BillException {
		
		return null;
	}

	@Override
	public Bill viewBill(Bill bill) throws BillException {
		
		return null;
	}

	@Override
	public List<Bill> viewBills(LocalDate startDate, LocalDate endDate) throws BillException {
		
		return null;
	}

	@Override
	public List<Bill> viewBill(String custId) throws BillException, CustomerException {
		
		return null;
	}

	@Override
	public Bill calculateTotalCost(Bill bill) throws BillException {
		
		return null;
	}

}
