package com.bdc.project.services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdc.project.entities.Customer;
import com.bdc.project.exception.UserNotFoundException;
import com.bdc.project.repository.CustomerRepositiry;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepositiry customerRepositiry;

	@Override
	public List<Customer> getCustomers() {
		return customerRepositiry.findAll();
	}

	@Override
	public Customer getCustomer(long customerId) throws UserNotFoundException {
		try {
		Customer cust  = customerRepositiry.findById(customerId).get();
		return cust;
		}catch(NoSuchElementException e) {
			throw new UserNotFoundException("CustomerId: "+customerId +" dosen't exist");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}

	@Override
	public List<Customer> addCustomer(Customer cust) {
		customerRepositiry.save(cust);
		return customerRepositiry.findAll();
	}



	@Override
	public List<Customer> deleteCustomer(long customerId) {
		customerRepositiry.deleteById(customerId);
		return customerRepositiry.findAll();
	}

	@Override
	public Customer updateCustomer(@Valid Customer cust, Long customerId) {
		cust.setId(customerId);
		
		Customer custObj = customerRepositiry.findById(customerId).get();
		
		custObj.setFirstName(cust.getFirstName());
		custObj.setLastName(cust.getLastName());
		custObj.setEmail(cust.getEmail());
		
		 customerRepositiry.save(custObj);
		return customerRepositiry.findById(customerId).get();
	}

	@Override
	public Customer addNewCustomer(Customer cust) {
		return customerRepositiry.save(cust);
	}
	
	/**
	 * GET CUSTOMER FROM ACCOUNT
	 */
//	@Override
//	public Long findCustomer(Long accountId) {
//
//		Long id = customerRepositiry.findCustomer(accountId);
//		
//
//		return id;
//	}

}
