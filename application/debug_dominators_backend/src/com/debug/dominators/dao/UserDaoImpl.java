package com.debug.dominators.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.debug.dominators.model.User;
import com.debug.dominators.utils.DatabaseUtils;

public class UserDaoImpl implements IUserDao {

	private Connection con;
	private static IUserDao userDao;

	private UserDaoImpl() throws ClassNotFoundException, SQLException {
		con = DatabaseUtils.getConnection();
	}

	public static IUserDao getUserDao() throws ClassNotFoundException, SQLException {
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}

	@Override
	public void addUsers(List<User> users) throws SQLException {

		String insertQuery = "INSERT INTO User (name, email, type_of_user_id, password, registered, last_login, imported_on) " +
		                     "VALUES (?, ?, ?, ?, ?, ?, ?) " +
		                     "ON DUPLICATE KEY UPDATE " +
		                     "name = VALUES(name), " +
		                     "type_of_user_id = VALUES(type_of_user_id), " +
		                     "password = VALUES(password), " +
		                     "registered = VALUES(registered), " +
		                     "last_login = VALUES(last_login), " +
		                     "imported_on = VALUES(imported_on)";

		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

		for (User user : users) {
		    preparedStatement.setString(1, user.getName());
		    preparedStatement.setString(2, user.getEmail());
		    preparedStatement.setInt(3, user.getTypeOfUserId());
		    preparedStatement.setString(4, user.getPassword());
		    preparedStatement.setBoolean(5, user.isRegistered());
		    preparedStatement.setString(6, user.getLastLogin());
		    preparedStatement.setTimestamp(7, user.getImportedOn() != null ? new java.sql.Timestamp(user.getImportedOn().getTime()) : null);
		    preparedStatement.addBatch();
		}

		preparedStatement.executeBatch();


	}

	@Override
	public void addUser(User user) throws SQLException {
		List<User> users = new ArrayList<>();
		users.add(user);
		addUsers(users);
	}

	@Override
	public void registerUser(String email, String role, String password) throws SQLException {
		String updateQuery = "UPDATE User SET password = ?, registered = true WHERE email = ? AND type_of_user_id = (SELECT role_id FROM Role WHERE role_name = ?)";
		PreparedStatement updateStatement = con.prepareStatement(updateQuery);
		updateStatement.setString(1, password);
		updateStatement.setBoolean(2, true);
		updateStatement.setString(3, email);
		updateStatement.setString(4, role);
		updateStatement.executeUpdate();

	}

	@Override
	public User getUserById(int id) throws SQLException {
		String selectQuery = "SELECT * FROM User WHERE user_id = ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			User user = new User();
			user.setUserId(resultSet.getInt("user_id"));
			user.setName(resultSet.getString("name"));
			user.setEmail(resultSet.getString("email"));
			user.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			user.setRegistered(resultSet.getBoolean("registered"));
			user.setLastLogin(resultSet.getString("last_login"));
			user.setImportedOn(resultSet.getDate("imported_on"));
			user.setRegisteredOn(resultSet.getDate("registered_on"));
			return user;
		}

		return null;
	}

	@Override
	public User getUserByUsernamePassword(String username, String password) throws SQLException {

		String selectQuery = "SELECT * FROM User WHERE email = ? and password = ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			User user = new User();
			user.setUserId(resultSet.getInt("user_id"));
			user.setName(resultSet.getString("name"));
			user.setEmail(resultSet.getString("email"));
			user.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			user.setRegistered(resultSet.getBoolean("registered"));
			user.setLastLogin(resultSet.getString("last_login"));
			user.setImportedOn(resultSet.getDate("imported_on"));
			user.setRegisteredOn(resultSet.getDate("registered_on"));
			return user;
		}

		return null;
	}

	@Override
	public User getUserByEmail(String email) throws SQLException {
		String selectQuery = "SELECT * FROM User WHERE email = ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			User user = new User();
			user.setUserId(resultSet.getInt("user_id"));
			user.setName(resultSet.getString("name"));
			user.setEmail(resultSet.getString("email"));
			user.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			user.setRegistered(resultSet.getBoolean("registered"));
			user.setLastLogin(resultSet.getString("last_login"));
			user.setImportedOn(resultSet.getDate("imported_on"));
			user.setRegisteredOn(resultSet.getDate("registered_on"));
			return user;
		}

		return null;
	}

}
