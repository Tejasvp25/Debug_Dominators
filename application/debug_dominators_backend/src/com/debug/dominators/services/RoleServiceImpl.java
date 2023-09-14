package com.debug.dominators.services;

import java.sql.SQLException;

import com.debug.dominators.dao.IRoleDao;
import com.debug.dominators.dao.RoleDaoImpl;
import com.debug.dominators.model.Role;

public class RoleServiceImpl implements IRoleService {

    private IRoleDao roleDao;

    public RoleServiceImpl() throws ClassNotFoundException, SQLException {
        roleDao = RoleDaoImpl.getRoleDao();
    }

    /**
     * Adds a role to the system.
     *
     * @param role The role to add.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void addRole(Role role) throws SQLException {
        roleDao.addRole(role);
    }
}
