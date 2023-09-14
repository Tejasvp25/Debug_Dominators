package com.debug.dominators.dao;

import java.sql.SQLException;

import com.debug.dominators.model.Role;

public interface IRoleDao {
	void addRole(Role role) throws SQLException;
}
