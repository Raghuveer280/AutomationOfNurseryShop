package com.example.demo.Repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.mimosa;

@Repository
public interface MimosaRepository extends CrudRepository<mimosa, String> {
	public List<mimosa> findBynameIgnoreCase(String name);

}
