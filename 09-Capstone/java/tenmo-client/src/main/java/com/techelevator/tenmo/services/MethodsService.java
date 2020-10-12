package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.view.ConsoleService;

public class MethodsService {

	private UserService userService;
	private TransferService transferService;
	private ConsoleService console;
	
	public MethodsService(UserService userService, ConsoleService console, TransferService transferService) {
		this.console = console;
		this.userService = userService;
		this.transferService = transferService;
	}
	
	public int getSelectedTransferId() {
		boolean isIdPositive = false;
		int selectedTransferId = 0;
		while(!isIdPositive) {
			try {
				selectedTransferId = console.getUserInputInteger("Enter a transfer ID to view a transfer.");
			} catch (Exception e) {
				System.out.println("Cannot parse user input to an integer.");
			}
			if(selectedTransferId > 0)
			isIdPositive = true;
		}
		return selectedTransferId;
	}
	
	public void displayTransfer(String authToken, Transfer transfer) {
		if(transfer != null) {
			System.out.println("Id: " + transfer.getTransferId() + "\nFrom: " + userService.getNameByAccountId(authToken, transfer.getAccountFrom()) + "\nTo: " + userService.getNameByAccountId(authToken, transfer.getAccountTo())  + "\nType: " +
					transferService.transferTypeByTypeId(authToken, transfer.getTransferType())  + "\nStatus: " + transferService.transferStatusByStatusId(authToken, transfer.getTransferStatusId())  + "\nAmount: " + transfer.getAmount());
		}else {
			System.out.println("There was no transfer found by that id.");
		}
	}
	
	public void displayListOfTransfers(int userId, Transfer[] listOfMyTransfers, String authToken) {
		for(Transfer transfer : listOfMyTransfers) {
			if (userId == transfer.getAccountFrom()) {
				String otherUsersName = userService.getNameByAccountId(authToken, transfer.getAccountTo());
				System.out.println(transfer.getTransferId() + "\t" + "To: " + otherUsersName + "\t" + "AMOUNT: $" + transfer.getAmount());
			}else {
				String otherUsersName = userService.getNameByAccountId(authToken, transfer.getAccountFrom());
				System.out.println(transfer.getTransferId() + "\t" + "From: " + otherUsersName + "\t" + "AMOUNT: $" + transfer.getAmount());
			}
		}
	}
	
	public User[] removeCurrentUserFromArray(User[] userArray,int userId) {
		List<User> userList = new ArrayList<>();
		for(User u : userArray) {
			if (u.getId() != userId)
			userList.add(u);
		}
		User[] userArrayFinal = new User[userList.size()];
		for(int i = 0; i < userArrayFinal.length; i++) {
			userArrayFinal[i] = userList.get(i);
		}
		return userArrayFinal;
	}
	
	public BigDecimal getMoneyToTransfer() {
		BigDecimal moneyToTransfer = BigDecimal.ZERO;
		while(moneyToTransfer == BigDecimal.ZERO) {
			try {
				moneyToTransfer = BigDecimal.valueOf(Double.parseDouble(console.getUserInput("How much money do you want to transfer?")));
				if(moneyToTransfer.compareTo(BigDecimal.ZERO) == -1) {
					moneyToTransfer = BigDecimal.ZERO;
					System.out.println("You cannot send a negative amount of money.");
				}
				if(moneyToTransfer.compareTo(BigDecimal.ZERO) == 0) {
					moneyToTransfer = BigDecimal.ZERO;
					System.out.println("You cannot send no money.");
				}
			} catch (Exception e) {
				System.out.println("Cannot parse input to a BigDecimal. Please try again.");
			}
		}
		return moneyToTransfer;
	}

}
