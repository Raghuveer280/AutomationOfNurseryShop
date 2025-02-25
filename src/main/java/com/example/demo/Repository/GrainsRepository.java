package com.example.demo.Repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Grains;

@Repository
public interface GrainsRepository extends CrudRepository<Grains, String>  {
	public List<Grains> findBynameIgnoreCase(String name);

}
