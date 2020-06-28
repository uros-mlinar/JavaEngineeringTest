package com.test.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Account;
import com.test.model.Farm;
import com.test.model.User;
import com.test.service.AccountService;
import com.test.service.FarmService;
import com.test.service.UserService;
import com.test.support.AccountToAccountDTO;
import com.test.support.FarmToFarmDTO;
import com.test.support.UserDtoToUser;
import com.test.support.UserToUserDTO;
import com.test.web.dto.AccountDTO;
import com.test.web.dto.FarmDTO;
import com.test.web.dto.UserDTO;

@RestController
@RequestMapping(value = "/api/users")
public class ApiUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserToUserDTO toDto;
	@Autowired
	private UserDtoToUser toUser;
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountToAccountDTO accToDto;
	@Autowired
	private FarmService farmService;
	@Autowired
	private FarmToFarmDTO farmToDto;

	// get all
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<UserDTO>> getAll() {

		List<User> users = userService.findAll();

		return new ResponseEntity<>(toDto.convert(users), HttpStatus.OK);
	}

	// get one
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<UserDTO> getOne(@PathVariable Long id) {

		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(user), HttpStatus.OK);
	}
	
	// get the currently authenticated user
	@RequestMapping(value = "/auth", method = RequestMethod.GET)
	ResponseEntity<UserDTO> getLoggedUser() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();

		User user = userService.findByName(name);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDto.convert(user), HttpStatus.OK);
	}

	// add
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<UserDTO> add(@RequestBody UserDTO toSave) {

		User saved = userService.save(toUser.convert(toSave));

		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
	}

	// delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<UserDTO> delete(@PathVariable Long id) {

		User deleted = userService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDTO>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
	}

	// edit
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	ResponseEntity<UserDTO> edit(@PathVariable Long id, @RequestBody UserDTO toEdit) {

		if (id != toEdit.getId())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		User edited = userService.save(toUser.convert(toEdit));

		return new ResponseEntity<>(toDto.convert(edited), HttpStatus.OK);
	}
	//find all accounts by user id
		@RequestMapping(value = "/{id}/accounts")
		ResponseEntity<List<AccountDTO>> findAccountByUserId(@PathVariable Long id){
			
			List<Account> accounts = accountService.findByUserId(id);
			
			return new ResponseEntity<>(accToDto.convert(accounts), HttpStatus.OK);	
		}
		
	//find all farms by user id
		@RequestMapping(value = "/{id}/farms")
		ResponseEntity<List<FarmDTO>> findFarmByUserId(@PathVariable Long id){
			
			List<Farm> farms = new ArrayList<>();
			List<Account> accounts = accountService.findByUserId(id);
			
			for (Account account : accounts) {
				farms.addAll(farmService.findByAccountId(account.getId()));			
			}			
			
			return new ResponseEntity<>(farmToDto.convert(farms), HttpStatus.OK);
		}
}
