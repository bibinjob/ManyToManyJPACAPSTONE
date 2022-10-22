package com.bdc.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.bdc.project.entities.Account;
import com.bdc.project.entities.Transfer;
import com.bdc.project.exception.BalanceNotEnoughException;
import com.bdc.project.repository.AccountRepositiry;

@Service
public class AccountServiceImpl implements AccountService {

	List<Account> list;

	public AccountServiceImpl() {

		list = new ArrayList<>();
		list.add(new Account(123456, "Savings", 3000));
		list.add(new Account(23457, "Current", 5000));
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	@Autowired
	private AccountRepositiry accountRepository;

	public Account getAccount(long accountId) {
		Account acc = null;

		try {
			acc = accountRepository.findById(accountId).get();
		} catch (Exception e) {

		}
		return acc;

	}

	@Override
	public List<Account> addAccounts(Account acc) {

		accountRepository.save(acc);

		return accountRepository.findAll();
	}

	@Override
	public Account addAccount(Account acc) {

		Account returnedAccount = accountRepository.save(acc);

		return returnedAccount;
	}
	
	
	
	
	
	
	@Override
	public Account updateAccount(Account acc, Long accountId) {
		Account accAccount = accountRepository.findById(accountId).get();
		accAccount.setAccountType(acc.getAccountType());
		accAccount.setBalance(acc.getBalance());
		accountRepository.save(accAccount);
		return accountRepository.findById(accountId).get();
	}

	
	/**
	 * DELETE AN ACCOUNT
	 */
	@Override
	public List<Account> deleteAccount(long accountId) {

		try {
			accountRepository.deleteById(accountId);
		} catch(DataIntegrityViolationException e) {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accountRepository.findAll();
	}
	
	/**
	 * TRANSFER FUND
	 */

	@Override
	public Account fundTransfer(Transfer transfer) throws BalanceNotEnoughException {

		Account accountFrom = accountRepository.findById(transfer.getFromAccount()).get();
		Account accountTo = accountRepository.findById(transfer.getToAccount()).get();
		Long transferAmount = transfer.getAmount();
		if (transfer.getFromAccount() == transfer.getToAccount()) {
			throw new BalanceNotEnoughException("From Account and To account must be different");
		} else if (accountFrom.getBalance() <= transferAmount) {
			throw new BalanceNotEnoughException("Balance in From Account is Less than Amount requested");
		} else if(transferAmount<=0){
			throw new BalanceNotEnoughException("Amount requested must be greater than '0'");
		}else {
			accountFrom.setBalance(accountFrom.getBalance() - transferAmount);
			accountTo.setBalance(accountTo.getBalance() + transferAmount);

			accountRepository.save(accountFrom);
			accountRepository.save(accountTo);
		}

		return accountFrom;
	}
	
	
	

}