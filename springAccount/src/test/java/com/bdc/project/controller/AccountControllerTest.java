package com.bdc.project.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bdc.project.entities.Account;
import com.bdc.project.repository.AccountRepositiry;
import com.bdc.project.services.AccountService;

@SpringBootTest
public class AccountControllerTest {

	@Autowired
	AccountService accountService;
	

	@MockBean
	private AccountRepositiry accountRepositiry;

	@Test
	public void getAccountTest() {
		when(accountRepositiry.findAll())
				.thenReturn(Stream.of(new Account(1, "Savings", 10000),new Account(2, "Savings", 10000)).collect(Collectors.toList()));
		assertEquals(2, accountService.getAccounts().size());
	}
//	
//	@Test
//	public void getAccountById() {
//		when(accountRepositiry.findById(1L))
//				.thenReturn(new Account(1, "Savings", 10000)).collect(Collectors.toList());
//		assertEquals(2, accountService.getAccounts().size());
//	}
	
	
	@Test
	public void createAccount() {
		List<Account> listAccount = new ArrayList<Account>();
		Account account = new Account( 1, "Savings", 10000);
		listAccount.add(account);
		when(accountRepositiry.save(account)).thenReturn(account);
		assertEquals(account, accountService.addAccounts(account));
	}
	
	@Test
	public void deleteAccount() {
		Account account = new Account(1, "Savings", 10000);
		accountRepositiry.delete(account);
		verify(accountRepositiry, times(1)).delete(account);
	}
	
	
	
	

}
