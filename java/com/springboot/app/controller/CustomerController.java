package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.entity.Customer;
import com.springboot.app.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;


	public CustomerController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}

	
	@GetMapping("/customers")
	public List<Customer> findAll() {
		return customerService.findAll();
	}


	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomerById(customerId);

		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}

		return theCustomer;
	}

		@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {


		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	@PutMapping("/customers/{customerId}")
	public Customer updateCustomer(@RequestBody Customer theCustomer,@PathVariable int customerId) {

		Customer tempCustomer = customerService.getCustomerById(customerId);

		
		if (tempCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}
		
		tempCustomer.setFirstName(theCustomer.getFirstName());
		tempCustomer.setLastName(theCustomer.getLastName());
		tempCustomer.setEmail(theCustomer.getEmail());
		
		customerService.saveCustomer(tempCustomer);
		
		theCustomer.setId(customerId);

		return theCustomer;
	}


	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		Customer tempCustomer = customerService.getCustomerById(customerId);


		if (tempCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}

		customerService.deleteById(customerId);

		return "Deleted customer id - " + customerId;
	}

}