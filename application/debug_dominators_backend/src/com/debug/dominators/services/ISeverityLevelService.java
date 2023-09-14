package com.debug.dominators.services;

import java.sql.SQLException;

import com.debug.dominators.model.SeverityLevel;

public interface ISeverityLevelService {
	void addSeverityLevel(SeverityLevel severityLevel)throws SQLException;
}
