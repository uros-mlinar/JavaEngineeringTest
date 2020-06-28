package com.test.service;

import java.util.List;

import com.test.model.Farm;

public interface FarmService {
	
	Farm findOne(Long id);
	List<Farm> findAll();
	Farm save(Farm toSave);
	Farm delete(Long id);
	List<Farm> findByAccountId(Long id);

}
