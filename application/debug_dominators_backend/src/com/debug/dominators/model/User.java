package com.debug.dominators.model;

import java.sql.Date;

public class User {
    private int userId;
    private String name;
    private String email;
    private int typeOfUserId;
    private String password;
    private boolean registered;
    private String lastLogin;
    private Date importedOn;
    private Date registeredOn;

    public User(){
		
	}
    
    public User(int userId, String name, String email, int typeOfUserId, boolean registered,
			 Date importedOn) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.typeOfUserId = typeOfUserId;
		this.registered = registered;
		this.importedOn = importedOn;
	}

    
    public User(int userId, String name, String email, int typeOfUserId, String password, boolean registered,
			String lastLogin, Date importedOn, Date registeredOn) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.typeOfUserId = typeOfUserId;
		this.password = password;
		this.registered = registered;
		this.lastLogin = lastLogin;
		this.importedOn = importedOn;
		this.registeredOn = registeredOn;
	}

	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTypeOfUserId() {
        return typeOfUserId;
    }

    public void setTypeOfUserId(int typeOfUserId) {
        this.typeOfUserId = typeOfUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getImportedOn() {
        return importedOn;
    }

    public void setImportedOn(Date importedOn) {
        this.importedOn = importedOn;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }
    
    public boolean validate() {    	
    	String nameRegex = "^[A-Za-z\\s'-]+$";
    	String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

    	return name.matches(nameRegex) &&  typeOfUserId >= 1 && typeOfUserId <= 3 && email.matches(emailRegex);
    }
    
    public boolean validateWithPassword() {
    	String passwordRegex = "^(?=.*[!@#$%^&*()_+\\-={}:;',.?/\\[\\]<>])(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
    	String password = "YourPassword123!"; // Replace with the actual password to validate

    	return validate() && password.matches(passwordRegex);

    }
}