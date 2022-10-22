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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Email;



@Entity
@Table (name="customers")
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private long id;
	@NotNull(message="firstName shouldnt be null")
	private String firstName;
	@NotNull(message="lastName shouldnt be null")
	private String lastName;
	@Email
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      })
	@JoinTable(
			name="customer_account",
			joinColumns = @JoinColumn(name = "customer_id"),
			inverseJoinColumns = @JoinColumn(name="account_id")
		)
	private Set<Account> accounts = new HashSet<>();

	
	public Customer() {}




	public Customer(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	


	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + "]";
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}



	public Set<Account> getCustomerAccounts() {
		return accounts;
	}
	

	public void addAccount(Account account) {
		accounts.add(account);
		
	}
	
	public void removeAccount(long accountId) {
	    Account tag = this.accounts.stream().filter(t -> t.getAccountNumber() == accountId).findFirst().orElse(null);
	    if (tag != null) {
	      this.accounts.remove(tag);
	      tag.getCustomers().remove(this);
	    }
	  }
	
	
}