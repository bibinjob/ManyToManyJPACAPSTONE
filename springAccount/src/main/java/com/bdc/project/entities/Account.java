package com.bdc.project.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="account")

public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private long id; 
	private String accountType;
	private long balance;
	
	
	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "accounts")
	@JsonIgnore
	private Set<Customer> customers = new HashSet<>();
	
	

	public Account(long id, String accountType, long balance) {
		super();
		
		this.id = id;
		this.accountType = accountType;
		this.balance = balance;
	}

	public Account() {
	}

	

	public long getAccountNumber() {
		return id;
	}

	public void setAccountNumber(long id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", balance=" + balance
				+ "]";
	}
	
	public Set<Customer> getCustomers() {
		return customers;
	}
	
	 public void setCustomers(Set<Customer> customers) {
		    this.customers = customers;
		  } 

	
	
}