package com.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.model.Customer;
import com.test.service.CustomerService;
import com.test.web.dto.CustomerDTO;

@Component
public class CustomerDtoToCustomer implements Converter<CustomerDTO, Customer> {

	@Autowired
	private CustomerService customerService;

	@Override
	public Customer convert(CustomerDTO dto) {
		Customer customer = null;

		//if ID is null, it's a new object to save
		if (dto.getId() == null) {
			customer = new Customer();
		//else it's an object from the DB	
		} else {
			customer = customerService.findOne(dto.getId());
			if (customer == null) {
				throw new IllegalArgumentException("Can not convert non-existant entity.");
			}
		}
		customer.setId(dto.getId());
		customer.setName(dto.getName());

		return customer;

	}

	public List<Customer> convert(List<CustomerDTO> source) {
		List<Customer> converted = new ArrayList<>();

		for (CustomerDTO dto : source) {
			converted.add(convert(dto));
		}

		return converted;

	}
}
