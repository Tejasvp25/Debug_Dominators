package com.debug.dominators.dao;

import java.sql.SQLException;

import com.debug.dominators.model.SeverityLevel;

public interface ISeverityLevelDao {
	void addSeverityLevel(SeverityLevel severityLevel) throws SQLException;
}
