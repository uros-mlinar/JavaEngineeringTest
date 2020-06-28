package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.model.Farm;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {
	
	List<Farm> findByAccountId (Long id);
}
