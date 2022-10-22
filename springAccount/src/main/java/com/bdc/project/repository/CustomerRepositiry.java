package com.bdc.project.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdc.project.entities.Customer;

@Repository
public interface CustomerRepositiry  extends JpaRepository<Customer, Long>{
	
	List<Customer> findCustomersByAccountsId(Long accountId);

}