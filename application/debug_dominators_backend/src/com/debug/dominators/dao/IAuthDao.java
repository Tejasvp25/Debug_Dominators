package com.debug.dominators.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import com.debug.dominators.model.Auth;

public interface IAuthDao {
    void authenticate(int userId) throws SQLException;
    boolean logout(int userId);
}

