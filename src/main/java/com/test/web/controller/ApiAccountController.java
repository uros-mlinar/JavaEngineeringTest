package com.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Account;
import com.test.service.AccountService;
import com.test.support.AccountDtoToAccount;
import com.test.support.AccountToAccountDTO;
import com.test.web.dto.AccountDTO;

@RestController
@RequestMapping(value = "/api/accounts")
public class ApiAccountController {
	
	@Autowired
	AccountService accountService;
	@Autowired
	AccountToAccountDTO toDto;
	@Autowired
	AccountDtoToAccount toAccount;

//get all
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AccountDTO>> getAll(){
		
		List<Account> accounts = accountService.findAll();
		
		return new ResponseEntity<>(toDto.convert(accounts), HttpStatus.OK);
	}
//get one	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<AccountDTO> getOne(@PathVariable Long id){
		
		Account account = accountService.findOne(id);
		if (account == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDto.convert(account), HttpStatus.OK);
	}
//add	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<AccountDTO> add(@RequestBody AccountDTO toSave){
		
		Account saved = accountService.save(toAccount.convert(toSave));
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}
//delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<AccountDTO> delete(@PathVariable Long id){
		
		Account deleted = accountService.delete(id);
		
		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<AccountDTO>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
	}
//edit
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	ResponseEntity<AccountDTO> edit(@PathVariable Long id, @RequestBody AccountDTO toEdit){
		
		if(id != toEdit.getId()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Account edited = accountService.save(toAccount.convert(toEdit));
		
		return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
	}

}
