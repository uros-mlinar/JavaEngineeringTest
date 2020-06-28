package com.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.model.Customer;
import com.test.web.dto.CustomerDTO;

@Component
public class CustomerToCustomerDTO implements Converter<Customer, CustomerDTO> {

	@Override
	public CustomerDTO convert(Customer source) {

		if(source == null) return null;
		
		CustomerDTO dto = new CustomerDTO();
		dto.setId(source.getId());
		dto.setName(source.getName());
		
		return dto;
	}
	
	public List<CustomerDTO> convert(List<Customer> source){
		List<CustomerDTO> converted = new ArrayList<>();
		
		for (Customer customer : source) {
			converted.add(convert(customer));
		}
		
		return converted;
	}

}
