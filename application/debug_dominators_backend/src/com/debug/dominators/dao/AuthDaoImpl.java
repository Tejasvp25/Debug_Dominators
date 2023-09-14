package com.debug.dominators.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.debug.dominators.utils.DatabaseUtils;

public class AuthDaoImpl implements IAuthDao{
    private Connection con;

    public AuthDaoImpl() throws ClassNotFoundException, SQLException {
        con = DatabaseUtils.getConnection();
    }

    @Override
    public void authenticate(int userId) throws SQLException {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, -24);
            Timestamp twentyFourHoursAgo = new Timestamp(cal.getTimeInMillis());

            // Check if the user's session exists and is still valid
            String query = "SELECT COUNT(*) FROM auth WHERE user_id = ? AND session_login >= ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setTimestamp(2, twentyFourHoursAgo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt(1) > 0) {
            	return;
            }
    
    }

    @Override
    public boolean logout(int userId) {
        try {
            // Delete the user's authentication entry
            String deleteQuery = "DELETE FROM auth WHERE user_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, userId);
            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0; // Return true if the entry was deleted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurred or the entry wasn't found
    }
}
