package com.debug.dominators.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.debug.dominators.model.Role;
import com.debug.dominators.utils.DatabaseUtils;

public class RoleDaoImpl implements IRoleDao {

	private Connection con;
	private static IRoleDao roleDao;

	private RoleDaoImpl() throws ClassNotFoundException, SQLException {
		con = DatabaseUtils.getConnection();
	}

	public static IRoleDao getRoleDao() throws ClassNotFoundException, SQLException {
		if (roleDao == null) {
			roleDao = new RoleDaoImpl();
		}
		return roleDao;
	}

	@Override
	public void addRole(Role role) throws SQLException {

		String insertQuery = "INSERT INTO Role (role_name) VALUES (?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

		preparedStatement.setString(1, role.getRoleName());

		preparedStatement.executeUpdate();
	}
}
