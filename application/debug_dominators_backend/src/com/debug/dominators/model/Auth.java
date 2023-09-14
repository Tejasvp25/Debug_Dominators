package com.debug.dominators.model;

import java.util.Date;

public class Auth {
    private int id;
    private int userId;
    private Date sessionLogin;
    
    
    
	public Auth() {
	}

	
	
	public Auth(int id, int userId, Date sessionLogin) {

		this.id = id;
		this.userId = userId;
		this.sessionLogin = sessionLogin;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getSessionLogin() {
		return sessionLogin;
	}
	public void setSessionLogin(Date sessionLogin) {
		this.sessionLogin = sessionLogin;
	}

}
