package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfer;

public interface AccountsDAO {

		BigDecimal getBalance(int accountId);
		
		List<Accounts> list();
		
		void updateBalances(Transfer transfer);
		
		Accounts getAccountUsingUserId(int userId);
}
