package com.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.model.Account;
import com.test.model.Customer;
import com.test.model.User;
import com.test.service.AccountService;
import com.test.service.CustomerService;
import com.test.service.UserService;
import com.test.web.dto.AccountDTO;

@Component
public class AccountDtoToAccount implements Converter<AccountDTO, Account> {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@Override
	public Account convert(AccountDTO dto) {

		Account account = null;
		User user = userService.findOne(dto.getUserId());
		Customer customer = customerService.findOne(dto.getCustomerId());

		if (user != null && customer != null) {
			//if ID is null, it's a new object to save
			if (dto.getId() == null) {
				account = new Account();
			//else it's an object from the DB		
			} else {
				account = accountService.findOne(dto.getId());
				if (account == null) {
					throw new IllegalArgumentException("Can not convert non-existant entity.");
				}
			}
			
			account.setId(dto.getId());
			account.setName(dto.getName());
			account.setCustomer(customer);
			account.setUser(user);

			return account;

		} else {
			throw new IllegalStateException("Trying to attach to a non-existant entity.");
		}
	}

	public List<Account> convert(List<AccountDTO> source) {
		List<Account> converted = new ArrayList<>();

		for (AccountDTO dto : source) {
			converted.add(convert(dto));
		}

		return converted;

	}
}
