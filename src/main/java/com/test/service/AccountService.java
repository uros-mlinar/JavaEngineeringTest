package com.test.service;

import java.util.List;

import com.test.model.Account;

public interface AccountService {

	Account findOne(Long id);
	List<Account> findAll();
	Account save(Account toSave);
	Account delete(Long id);
	List<Account> findByUserId(Long id);
}
