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
public class payment {
	@Id
	int Orderid;
	String Fname;
	String email;
	int amt;
	String modeofpayment;
	String address;
	String city;
	String state;
	int pincode;
	public payment(){}
}
