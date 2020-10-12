package com.techelevator.tenmo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.dao.UserSqlDAO;
import com.techelevator.tenmo.model.User;

@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
@RestController
public class UserController {

	private UserDAO userDAO;
	
	public UserController(UserSqlDAO userSqlDAO) {
		this.userDAO = userSqlDAO;
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> list() {
        return userDAO.findAll();
    }
	
    @RequestMapping(path = "/getname", method = RequestMethod.POST)
    public String sendTransfer(@Valid @RequestBody int accountId){
    	String returnString = userDAO.findNameByAccountId(accountId);
        return returnString;
    }
	
}
