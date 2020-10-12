package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.tenmo.model.Transfer;

@Component
public class TransferSqlDAO implements TransferDAO{

	private JdbcTemplate jdbcTemplate;
	
	public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;		
	}
	
	@Override
	public Transfer insertTransfer(Transfer transfer) {
		String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id,account_from,account_to,amount) VALUES (?,?,?,?,?);";
		jdbcTemplate.update(sql, transfer.getTransferType(), transfer.getTransferStatusId(),transfer.getAccountFrom(),transfer.getAccountTo(),transfer.getAmount());
		return transfer;
	}
	
	@Override
	public Transfer getTransferByTransferId(int transferId) {
		String sql = "SELECT * FROM transfers WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,transferId);
        results.next();
        Transfer transfer = mapRowToTransfer(results);
        return transfer;
	}
	
	@Override
	public List<Transfer> getTransfersByUserId(int userId){	
		List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfers WHERE account_from = ? OR account_to = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId,userId);
        while(results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
	}
	
	@Override
	public List<Integer> getAllTransferIds(){
		List<Integer> transferIds = new ArrayList<>();
        String sql = "SELECT transfer_id FROM transfers;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            int transferId = results.getInt("transfer_id");
            transferIds.add(transferId);
        }
        return transferIds;
	}
	@Override
	public String getTypeByTypeId(int typeId) {
		String sql = "SELECT transfer_type_desc FROM transfer_types WHERE transfer_type_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,typeId);
		results.next();
		String type = results.getString("transfer_type_desc");
		return type;
	}
	
	@Override
	public String getStatusByStatusId(int statusId) {
		String sql = "SELECT transfer_status_desc FROM transfer_statuses WHERE transfer_status_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,statusId);
		results.next();
		String status = results.getString("transfer_status_desc");
		return status;
	}
	
	private Transfer mapRowToTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setTransferStatusId(rs.getInt("transfer_status_id"));
        transfer.setTransferType(rs.getInt("transfer_type_id"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
    }
}
