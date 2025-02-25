package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.payment;

@Repository
public interface PaymentRepository extends CrudRepository<payment, Integer> {
	
}
