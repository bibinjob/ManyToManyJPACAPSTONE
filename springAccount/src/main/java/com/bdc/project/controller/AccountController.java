package com.bdc.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.project.entities.Account;
import com.bdc.project.entities.Customer;
import com.bdc.project.repository.AccountRepositiry;
import com.bdc.project.repository.CustomerRepositiry;
import com.bdc.project.services.AccountService;
import com.bdc.project.exception.ResourceNotFoundException;

@RestController
public class AccountController {
	
	@GetMapping("/accountss")
	public String getTest() {
		return "account";
	}

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepositiry accountRepositiry;
	@Autowired
	private CustomerRepositiry customerRepositiry;

	/**
	 **********************************************
	 ************ ACCOUNT APIS ********************
	 **********************************************
	 */

	// GET ALL ACCOUNTS
	@GetMapping("/accounts")
	public List<Account> getAccounts() {
		return this.accountService.getAccounts();
	}

	// GGET ACCOUNT WITH ID
	@GetMapping("/account/{accountId}")
	public Account getAccount(@PathVariable String accountId) {
		return this.accountService.getAccount(Long.parseLong(accountId));
	}

	// CREATE/ADD NEW ACCOUNT
	@PostMapping(path = "/accounts", consumes = "application/json")
	public List<Account> addAccount(@RequestBody Account acc) {
		return accountService.addAccounts(acc);
	}

	// CREATE/ADD NEW ACCOUNT
	@PostMapping(path = "/accounts2", consumes = "application/json")
	public ResponseEntity<List<Account>> addAccount2(@RequestBody Account acc) {
		return ResponseEntity.ok(accountService.addAccounts(acc));
	}

	// CREATE/ADD NEW ACCOUNT
	@PutMapping(path = "/account/{accountId}", consumes = "application/json")
	public Account updateAccount(@PathVariable Long accountId, @RequestBody Account acc) {
		return accountService.updateAccount(acc, accountId);
	}

	// DELETE ACCOUNT WITH ID
	@DeleteMapping("/account/{accountId}")
	public List<Account> deleteAccount(@PathVariable String accountId) {
		return accountService.deleteAccount(Long.parseLong(accountId));
	}
	
	@GetMapping("/accounts/{accountId}/customers")
	  public ResponseEntity<List<Customer>> getAllTutorialsByTagId(@PathVariable(value = "accountId") Long accountId) throws ResourceNotFoundException {
	    if (!accountRepositiry.existsById(accountId)) {
	      throw new ResourceNotFoundException("Account Not found with AccountId = " + accountId);
	    }

	    List<Customer> tutorials = customerRepositiry.findCustomersByAccountsId(accountId);
	    return new ResponseEntity<>(tutorials, HttpStatus.OK);
	  }
	


}
