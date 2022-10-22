package com.bdc.project.services;

import java.util.List;

import com.bdc.project.entities.Account;
import com.bdc.project.entities.Transfer;
import com.bdc.project.exception.BalanceNotEnoughException;

public interface AccountService {
	
	public List<Account> getAccounts();
	
	
	public Account getAccount(long accountId);
	
	public List<Account> addAccounts(Account acc);
	
	public Account addAccount(Account acc) ;
	public Account updateAccount(Account acc, Long accountId) ;
	
	public List<Account> deleteAccount(long accountId);
	
	public Account fundTransfer(Transfer transfer) throws BalanceNotEnoughException;
	
}