package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.contactus;

@Repository
public interface contactUsRepository extends CrudRepository<contactus, String> {

}
