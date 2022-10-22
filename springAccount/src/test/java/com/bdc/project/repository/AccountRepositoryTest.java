package com.bdc.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bdc.project.entities.Account;
import com.bdc.project.services.AccountService;

@SpringBootTest
class AccountRepositoryTest {

	@Autowired
	private AccountRepositiry accountRepository;
	@Autowired
	private AccountService accountService;
	
	
	@Test
	void createAccount() {
		
		Account account = new Account();
		account.setAccountNumber(12347);
		account.setAccountType("Saving");
		account.setBalance(200000);
		Account savedObject = accountRepository.save(account);
		
		System.out.println(savedObject.getAccountNumber());
		System.out.println(savedObject.getAccountType());
		
	}
	
	@Test
	void findbyAccountNumber() {
		Long id = 1L;
		
		accountRepository.findById(id).get();
	}
	
	
	@Test
	void findAccWithCust() {
		//accountService.getAccountList();
	}
}
