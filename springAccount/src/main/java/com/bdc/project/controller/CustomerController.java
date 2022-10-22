package com.bdc.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.project.entities.Customer;
import com.bdc.project.exception.UserNotFoundException;
import com.bdc.project.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	/**
	 **********************************************
	 ************ CUSTOMER APIS *******************
	 **********************************************
	 */

	// Get ALL CUSTOMERS
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return this.customerService.getCustomers();
	}

	// GGET CUSTOMER WITH ID
	@GetMapping("/customer/{customerId}")
	public Customer getCustomer(@PathVariable Long customerId) throws UserNotFoundException {
		return this.customerService.getCustomer(customerId);
	}

	// CREATE/ADD NEW CUSTOMER
	@PostMapping(path = "/customer", consumes = "application/json")
	public ResponseEntity<List<Customer>> addCustomer(@RequestBody @Valid Customer cust) {
		return ResponseEntity.ok(customerService.addCustomer(cust));
	}

	// DELETE Customer WITH ID
	@DeleteMapping("/customer/{customerId}")
	public List<Customer> deleteCustomer(@PathVariable String customerId) {
		return this.customerService.deleteCustomer(Long.parseLong(customerId));
	}

	// Update Customer details
	@PutMapping(path = "/customer/{customerId}", consumes = "application/json")
	public Customer updateCustomer(@PathVariable Long customerId, @RequestBody @Valid Customer cust) {
		return customerService.updateCustomer(cust, customerId);
	}
}
