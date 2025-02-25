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
public class contactus {
	@Id
	String name;
	String email;
	String msg;
	public contactus() {}
}
