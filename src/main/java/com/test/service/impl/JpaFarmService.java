package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Farm;
import com.test.repository.FarmRepository;
import com.test.service.FarmService;

@Service
@Transactional
public class JpaFarmService implements FarmService {

	@Autowired
	private FarmRepository farmRepository;
	
	@Override
	public Farm findOne(Long id) {
		return farmRepository.findById(id).orElse(null);
	}

	@Override
	public List<Farm> findAll() {
		return farmRepository.findAll();
	}

	@Override
	public Farm save(Farm toSave) {
		return farmRepository.save(toSave);
	}

	@Override
	public Farm delete(Long id) {
		Farm toDelete = farmRepository.getOne(id);
		farmRepository.delete(toDelete);
		return toDelete;
	}

	@Override
	public List<Farm> findByAccountId(Long id) {
		return farmRepository.findByAccountId(id);
	}

}
