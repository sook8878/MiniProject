package com.mini.controller;

import java.util.List;

import javax.accessibility.AccessibleContext;

import com.mini.model.dto.GameInfotmationDTO;
import com.mini.service.UserService;

public class UserController {
	
	private UserService userservice = new UserService();
	
	public void saveUser(String userName) {
		
	
		userservice.saveUser(userName);
		

	}


	public List<GameInfotmationDTO> rankAllUser() {
		return userservice.rankAllUser();
		
	}


	public void savaStage(String name , int stage, int lovePoint) {
		
		userservice.savaStage(name,stage,lovePoint);
		
	}
	




}
