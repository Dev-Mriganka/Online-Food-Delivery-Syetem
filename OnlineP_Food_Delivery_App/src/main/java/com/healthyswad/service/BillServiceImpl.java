package com.healthyswad.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.healthyswad.exception.BillException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.model.Bill;
import com.healthyswad.repository.BillRepo;

@Service
public class BillServiceImpl implements BillService{
	
	private BillRepo br;

	@Override
	public Bill addBill(Bill bill) throws BillException {
		Bill b = br.save(bill);
		
		return b;
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		br.findById(bill.getBillId()).orElseThrow(()-> new BillException("No bills available with this id"));
		return br.save(bill);
	}

	@Override
	public Bill removeBill(Bill bill) throws BillException {
		Bill bi=br.findById(bill.getBillId()).orElseThrow(() -> new BillException("No bills available....."));
		br.delete(bill);
		return bi;
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
