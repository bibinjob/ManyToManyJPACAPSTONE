package com.bdc.project.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdc.project.entities.Account;
import com.bdc.project.entities.Customer;
import com.bdc.project.entities.CustomerAccount;
import com.bdc.project.entities.Transfer;
import com.bdc.project.exception.BalanceNotEnoughException;
import com.bdc.project.exception.UserNotFoundException;
import com.bdc.project.repository.AccountRepositiry;
import com.bdc.project.repository.CustomerRepositiry;
import com.bdc.project.services.AccountService;
import com.bdc.project.services.CustomerService;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class TransferController {

	
	/**
	 **********************************************
	 ************ CUSTOMER REF APIS *******************
	 **********************************************
	 */
	@Autowired
	private AccountService accountService;
	@Autowired
	private CustomerService customerService;

	@Autowired
	CustomerRepositiry customerRepositiry;
	@Autowired
	AccountRepositiry accountRepositiry;

	@PutMapping("/customers/{custId}/accounts/{accId}")
	public Customer xrefCustomer(@PathVariable Long custId, @PathVariable Long accId) {
		Customer customer = customerRepositiry.findById(custId).get();
		Account account = accountRepositiry.findById(accId).get();
		customer.addAccount(account);
		return customerRepositiry.save(customer);
	}

	@PostMapping("/customers/{custId}/accounts")
	public Customer xrefCustomers(@RequestBody Account acc, @PathVariable Long custId) throws UserNotFoundException {
		Customer customer = customerService.getCustomer(custId);
		if (customer != null) {
			Account account = accountService.addAccount(acc);
			customer.addAccount(account);
			return customerRepositiry.save(customer);
		} else {
			throw new UserNotFoundException("CustomerId: " + custId + " not found");
		}
	}

	@PostMapping("/customerAccounts")
	public Customer customerAccounts(@RequestBody @Valid CustomerAccount custAcc) throws UserNotFoundException, JsonMappingException {
		Long custId = 0L;
		int size = 0;
		try {
			size = custAcc.getAccounts().size();

		} catch (Exception e) {
			throw new UserNotFoundException("Invalid Input ");
		}

		if (size > 0) {
			Customer customer = new Customer();
			try {
				customer.setFirstName(custAcc.getFirstName());
				customer.setLastName(custAcc.getLastName());
				customer.setEmail(custAcc.getEmail());
				customer = customerService.addNewCustomer(customer);
				custId = customer.getId();
				System.out.println(custId + " Customer Created");
				ArrayList<Account> accountList = custAcc.getAccounts();
				if (size > 0) {
					for (int i = 0; i < size; i++) {
						Account addAccount = new Account();
						System.out.println(""+addAccount);
						addAccount.setAccountType(accountList.get(i).getAccountType());
						System.out.println(""+accountList.get(i).getAccountType());
						addAccount.setBalance(accountList.get(i).getBalance());
						System.out.println(""+accountList.get(i).getAccountType());
						Account newAccount = accountService.addAccount(addAccount);
						System.out.println(""+newAccount.getAccountNumber());
						customer.addAccount(newAccount);
						customerRepositiry.save(customer);
						System.out.println(newAccount.getAccountNumber() + " Added to " + customer.getId());
					}
				}
				return customerRepositiry.findById(custId).get();
			} catch (NoSuchElementException e) {
				throw new UserNotFoundException("Invalid/Incomplete input");
			} catch (NullPointerException e) {
				throw new UserNotFoundException("invalid fields cannot be null or empty");
			} catch (Exception e) {
				throw new UserNotFoundException("Invalid Input");
			}
		} else {
			throw new UserNotFoundException("Customer cannot be created without an Account");
		}

	}
	
	public boolean validateInput(CustomerAccount custAcc) {
		int size = 0;
		try {
			size = custAcc.getAccounts().size();
			Customer customer = new Customer();
			customer.setFirstName(custAcc.getFirstName());
			customer.setLastName(custAcc.getLastName());
			customer.setEmail(custAcc.getEmail());
			ArrayList<Account> accountList = custAcc.getAccounts();
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					Account addAccount = new Account();
					addAccount.setAccountType(accountList.get(i).getAccountType());
					addAccount.setBalance(accountList.get(i).getBalance());
				}
			}
		}catch(Exception e) {
			
		}
		return false;
	}

	/**
	 **********************************************
	 ************ TRANSFER *******************
	 **********************************************
	 */

	@PutMapping("/accounts/transferFund")
	public Account fundTransfer(@RequestBody Transfer transaction) throws BalanceNotEnoughException {
		return this.accountService.fundTransfer(transaction);
	}

}
