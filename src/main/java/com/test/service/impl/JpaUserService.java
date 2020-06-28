package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.User;
import com.test.repository.UserRepository;
import com.test.service.UserService;

@Service
@Transactional
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User toSave) {
		return userRepository.save(toSave);
	}

	@Override
	public User delete(Long id) {
		User toDelete = userRepository.getOne(id);
		userRepository.delete(toDelete);
		return toDelete;
	}

	@Override
	public User findByName(String name) {
		return userRepository.findByName(name);
	}

}
