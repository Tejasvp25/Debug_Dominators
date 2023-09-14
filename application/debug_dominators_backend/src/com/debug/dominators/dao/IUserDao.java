package com.debug.dominators.dao;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.User;

public interface IUserDao {
	void addUsers(List<User> users)  throws SQLException;
	void addUser(User user)  throws SQLException;
	void registerUser(String email, String role, String password) throws SQLException;
	User getUserById(int id)  throws SQLException;
	User getUserByUsernamePassword(String username, String password) throws SQLException;
	User getUserByEmail(String email) throws SQLException;
}
