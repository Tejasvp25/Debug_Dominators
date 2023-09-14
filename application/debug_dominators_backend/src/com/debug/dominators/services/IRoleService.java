package com.debug.dominators.services;

import java.sql.SQLException;

import com.debug.dominators.model.Role;

public interface IRoleService {
	void addRole(Role role) throws SQLException;
}
