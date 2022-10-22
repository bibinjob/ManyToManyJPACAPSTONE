package com.bdc.project.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bdc.project.entities.Customer;
import com.bdc.project.exception.UserNotFoundException;

@Service("customerService")
public interface CustomerService {

	
public List<Customer> getCustomers();
	
	
	public Customer getCustomer(long customerId) throws UserNotFoundException;
	
	public List<Customer> addCustomer(Customer cust);
	
	public Customer addNewCustomer(Customer cust);
	
	public List<Customer> deleteCustomer(long customerId);


	public  Customer updateCustomer(@Valid Customer cust, Long customerId);
	
//	public Long findCustomer(Long accountId);
}
