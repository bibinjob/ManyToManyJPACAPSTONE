package com.bdc.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bdc.project.entities.Customer;

@SpringBootTest
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepositiry customerRepositiry;

	@Test
	void createCustomer() {
		Customer customer = new Customer();
		customer.setId(0);
		customer.setFirstName("Bibin");
		customer.setLastName("job");
		customer.setEmail("bibin@gmail.com");

		Customer savedCustomer = customerRepositiry.save(customer);

		System.out.println(savedCustomer.getId());
		System.out.println(savedCustomer.getEmail());

	}

}
