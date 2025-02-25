package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.payment;
import com.example.demo.Repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository paymentrepo;
	
	public payment create(payment p){
		return paymentrepo.save(p);
	}
	public long countPayment()
	{
		return paymentrepo.count();
	}
	public Iterable<payment> showPayment()
	{
		return paymentrepo.findAll();
	}
	public void deletePayment(int oid)
	{
		paymentrepo.deleteById(oid);
	}
	public void deleteAllpayment()
	{
		paymentrepo.deleteAll();
	}
}
