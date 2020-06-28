package com.test.service;

import java.util.List;

import com.test.model.Customer;

public interface CustomerService {
	
	Customer findOne(Long id);
	List<Customer> findAll();
	Customer save(Customer toSave);
	Customer delete(Long id);

}
