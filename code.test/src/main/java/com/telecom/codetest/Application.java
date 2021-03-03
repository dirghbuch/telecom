package com.telecom.codetest;

import com.telecom.codetest.models.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {

	// Assuming the data will be loaded into following set
	public static Set<Customer> customerSet = new HashSet<>();
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
