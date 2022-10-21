package com.springboot.app.service;

import java.util.List;

import com.springboot.app.entity.Customer;

public interface CustomerService {
	List<Customer> findAll();
	
	Customer saveCustomer(Customer customer);
	
	Customer getCustomerById(int id);
	
	Customer updateCustomer(Customer customer);
	
	void deleteById(int id);

}