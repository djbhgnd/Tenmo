package com.techelevator.tenmo.model;

import java.math.BigDecimal;

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
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Transfer() {
		
	}
	
	public Transfer(int transferId, int transferType, int transferStatusId, int accountFrom, int accountTo, BigDecimal amount) {
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.transferId = transferId;
		this.transferType = transferType;
		this.transferStatusId = transferStatusId;
		this.amount = amount;
		
	}
}
