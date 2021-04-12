package com.mini.model.dto;

import java.sql.Date;

public class GameInfotmationDTO {
	
	private String username;
	private java.sql.Date playdate;
	private int stage;
	private int heart;
	private int numberid;
	
	
	public GameInfotmationDTO() {
		super();
	
	}



	public GameInfotmationDTO(String username, Date playdate, int stage, int heart, int numberid) {
		super();
		this.username = username;
		this.playdate = playdate;
		this.stage = stage;
		this.heart = heart;
		this.numberid = numberid;
	}


	
	@Override
	public String toString() {
		return "GameInfotmationDTO [username=" + username + ", playdate=" + playdate + ", stage=" + stage + ", heart="
				+ heart + ", numberid=" + numberid + "]";
	}


	public String getUsername() {
		return username;
	}


	
	public void setUsername(String username) {
		this.username = username;
	}


	
	public java.sql.Date getPlaydate() {
		return playdate;
	}



	public void setPlaydate(java.sql.Date playdate) {
		this.playdate = playdate;
	}



	public int getStage() {
		return stage;
	}



	public void setStage(int stage) {
		this.stage = stage;
	}


	
	public int getHeart() {
		return heart;
	}



	public void setHeart(int heart) {
		this.heart = heart;
	}



	public int getNumberid() {
		return numberid;
	}



	public void setNumberid(int numberid) {
		this.numberid = numberid;
	}
	
	 
}
