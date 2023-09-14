package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.User;
import com.debug.dominators.services.exceptions.InvalidUsernamePasswordException;
import com.debug.dominators.services.exceptions.RoleMismatchException;
import com.debug.dominators.services.exceptions.UserAlreadRegisteredException;
import com.debug.dominators.services.exceptions.UserNotFoundException;
import com.debug.dominators.services.exceptions.UserNotRegisteredException;
import com.debug.dominators.services.exceptions.WeakPasswordException;

public interface IUserService {
	void addUsers(List<User> users) throws SQLException;
	void addUser(User user)throws SQLException;
	void registerUser(String email, String role, String password)throws SQLException, UserNotRegisteredException, UserAlreadRegisteredException, RoleMismatchException, WeakPasswordException;
	User getUserById(int id)throws SQLException, UserNotFoundException;
	boolean login(String username, String password) throws SQLException, InvalidUsernamePasswordException;
}
