package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.Accounts;
import com.techelevator.tenmo.model.Transfer;

@RequestMapping("/transfer")
@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

	private AccountsDAO accountDAO;
	private UserDAO userDAO;
	private TransferDAO transferDAO;
	
	public TransferController(AccountsDAO accountDAO, UserSqlDAO userSqlDAO, TransferDAO transferDAO) {
		this.accountDAO = accountDAO;
		this.userDAO = userSqlDAO;
		this.transferDAO = transferDAO;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Transfer sendTransfer(@Valid @RequestBody Transfer transfer){
		transfer.setTransferStatusId(2);
		transfer.setTransferType(2);
		Accounts account = accountDAO.getAccountUsingUserId(transfer.getAccountFrom());
		int compare = account.getBalance().compareTo(transfer.getAmount());
		Transfer updatedTransfer = null;
		if(compare == 0 || compare == 1) {
			updatedTransfer = transferDAO.insertTransfer(transfer);
		}
		if(updatedTransfer != null) {
			accountDAO.updateBalances(updatedTransfer);
		}
        return updatedTransfer;
    }
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Transfer getTransferFromTransferId(@PathVariable int id){
		Transfer transfer = null;
		List<Integer> transferIds = transferDAO.getAllTransferIds();
		if(transferIds.contains(id)) {
			transfer = transferDAO.getTransferByTransferId(id);
		}		
		return transfer;
	}
	
	@RequestMapping(path = "/mytransfers", method = RequestMethod.GET)
	public List<Transfer> getTransfersForUser(Principal principal){
		List<Transfer> listOfTransfersInvolvingUser = transferDAO.getTransfersByUserId(userDAO.findIdByUsername(principal.getName()));
		return listOfTransfersInvolvingUser;
	}
	
	@RequestMapping(path = "/gettype", method = RequestMethod.POST)
	public String getTypeFromTypeId(@Valid @RequestBody int typeId){
		return transferDAO.getTypeByTypeId(typeId);
	}
	
	@RequestMapping(path = "/getstatus", method = RequestMethod.POST)
	public String getStatusFromStatusId(@Valid @RequestBody int statusId){
		return transferDAO.getStatusByStatusId(statusId);
	}
}
