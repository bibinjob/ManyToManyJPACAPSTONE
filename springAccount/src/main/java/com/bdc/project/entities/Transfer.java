package com.bdc.project.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Transfer {
	
	@NotNull(message = "From account cannot be null")
	private Long fromAccount;
	@NotNull(message = "To account cannot be null")
	private Long toAccount;
	@Min(0)
	private Long amount;
	public Long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Long getToAccount() {
		return toAccount;
	}
	public void setToAccount(Long toAcccount) {
		this.toAccount = toAcccount;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	

}