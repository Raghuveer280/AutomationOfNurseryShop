package com.example.demo.Entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Setter
@Getter
public class cart {
	@Id
	String name;
	int price;
	int quantity;
	String picurl;
	public cart() {}
	
}
