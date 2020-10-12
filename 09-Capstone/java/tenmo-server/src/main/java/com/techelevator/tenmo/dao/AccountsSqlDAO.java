package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfer;

@Component
public class AccountsSqlDAO implements AccountsDAO{

	private JdbcTemplate jdbcTemplate;
	
    public AccountsSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public BigDecimal getBalance(int userId) {
        return jdbcTemplate.queryForObject("select balance from accounts where user_id = ?;", BigDecimal.class, userId);
    }
    
    @Override
    public List<Accounts> list() {
    	List<Accounts> accountsList = new ArrayList<Accounts>();
    	String sql = "select * from accounts;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
        	Accounts account = mapRowToAccount(results);
        	accountsList.add(account);
        }
        return accountsList;
    }
    
    @Override
    public void updateBalances(Transfer transfer) {
    	String sql = "UPDATE accounts SET balance = balance + ? WHERE user_id = ?;";
    	jdbcTemplate.update(sql,transfer.getAmount(),transfer.getAccountTo());
    	jdbcTemplate.update(sql,transfer.getAmount().negate(),transfer.getAccountFrom());
    }
    
    @Override
    public Accounts getAccountUsingUserId(int userId) {
    	String sql = "select * from accounts where user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        Accounts account = null;
        results.next();
        account = mapRowToAccount(results);
    	return account;
    }
    
    private Accounts mapRowToAccount(SqlRowSet results) {
    	Accounts account = new Accounts();
		account.setUserId(results.getInt("user_id"));
		account.setAccountId(results.getInt("account_id"));
		account.setBalance(results.getBigDecimal("balance"));
		return account;
    }

}
