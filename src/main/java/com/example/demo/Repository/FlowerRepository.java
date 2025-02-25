package com.example.demo.Repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.flower;

@Repository
public interface FlowerRepository extends CrudRepository<flower, String>{
	public List<flower> findBynameIgnoreCase(String name);

}
