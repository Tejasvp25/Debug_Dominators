package com.debug.dominators.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.debug.dominators.model.SeverityLevel;
import com.debug.dominators.utils.DatabaseUtils;

public class SeverityLevelDaoImpl implements ISeverityLevelDao {

	private Connection con;
	private static ISeverityLevelDao severityLevelDao;

	private SeverityLevelDaoImpl() throws ClassNotFoundException, SQLException {
		con = DatabaseUtils.getConnection();
	}

	public static ISeverityLevelDao getSeverityLevelDao() throws ClassNotFoundException, SQLException {
		if (severityLevelDao == null) {
			severityLevelDao = new SeverityLevelDaoImpl();
		}
		return severityLevelDao;
	}

	@Override
	public void addSeverityLevel(SeverityLevel severityLevel) throws SQLException {

		String insertQuery = "INSERT INTO SeverityLevel (name) VALUES (?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

		preparedStatement.setString(1, severityLevel.getName());

		preparedStatement.executeUpdate();

	}
}
