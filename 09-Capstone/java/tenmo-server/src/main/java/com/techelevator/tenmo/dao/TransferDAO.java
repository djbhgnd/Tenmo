package com.techelevator.tenmo.dao;

import java.util.List;
import com.techelevator.tenmo.model.Transfer;

public interface TransferDAO {
	
	Transfer insertTransfer(Transfer transfer);
	
	Transfer getTransferByTransferId(int transferId);
	
	List<Transfer> getTransfersByUserId(int userId);
	
	List<Integer> getAllTransferIds();
	
	String getTypeByTypeId(int typeId);
	
	String getStatusByStatusId(int statusId);
}
