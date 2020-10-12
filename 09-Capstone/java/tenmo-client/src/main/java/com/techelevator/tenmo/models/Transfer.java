package com.techelevator.tenmo.models;

import java.math.BigDecimal;
import java.security.Principal;

public class Transfer {

	private int transferId;
	private int transferType;
	private int transferStatusId;
	private int accountFrom;
	private int accountTo;
	private BigDecimal amount;
	
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getTransferType() {
		return transferType;
	}
	public void setTransferType(int transferType) {
		this.transferType = transferType;
	}
	public int getTransferStatusId() {
		return transferStatusId;
	}
	public void setTransferStatusId(int transferStatusId) {
		this.transferStatusId = transferStatusId;
	}
	public int getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}
	public int getAccountTo() {
		return accountTo;
	}
	public void setAccount_to(int accountTo) {
		this.accountTo = accountTo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Transfer(String accountFrom, String accountTo, String amount) {
		this.accountFrom = Integer.parseInt(accountFrom);
		this.accountTo = Integer.parseInt(accountTo);
		this.amount = BigDecimal.valueOf(Double.parseDouble(amount));
	}
	
	public Transfer() {
		
	}
}
