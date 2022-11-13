package com.healthyswad.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyswad.exception.BillException;
import com.healthyswad.exception.CustomerException;
import com.healthyswad.model.Bill;
import com.healthyswad.repository.BillRepo;

@Service
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillRepo billRepo;

	@Override
	public Bill addBill(Bill bill) throws BillException {
		
		if(bill==null)
			throw new BillException("Bill Can't be Null");
		
		return billRepo.save(bill);
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
	
		Optional<Bill> opt = billRepo.findById(bill.getBillId());
		
		if(opt.isPresent()) {
			return billRepo.save(opt.get());
		}else
			throw new BillException("Bill Not Exist to Update ");
		
		
	}

	@Override
	public Bill removeBill(Bill bill) throws BillException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill viewBill(Integer billId) throws BillException {
		
		Optional<Bill> opt = billRepo.findById(billId);
		
		if(opt.isPresent())
			throw new BillException("Bill Doest Not Exist ");
		
		return opt.get();
	}

	@Override
	public List<Bill> viewBillsBetweenDates(LocalDate startDate, LocalDate endDate) throws BillException {
		

		List<Bill> bills = billRepo.viewAllBillsBetweenDates(startDate, endDate);

		
		if(bills.isEmpty())
			throw new BillException("No Bills Found ");
		return bills;
	}

	@Override
	public List<Bill> viewBills(Integer custId) throws BillException, CustomerException {
		return null;
	}

	@Override
	public Bill calculateTotalCost(Bill bill) throws BillException {
		return null;
		
	}

}
