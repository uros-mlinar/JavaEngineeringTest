package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Customer;
import com.test.repository.CustomerRepository;
import com.test.service.CustomerService;

@Service
@Transactional
public class JpaCustomerService implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer findOne(Long id) {
		return customerRepository.findById(id).orElse(null);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer toSave) {
		return customerRepository.save(toSave);
	}

	@Override
	public Customer delete(Long id) {
		Customer toDelete = customerRepository.getOne(id);
		customerRepository.delete(toDelete);
		return toDelete;
	}

}
