package com.debug.dominators.dao;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.Bug;
import com.debug.dominators.services.exceptions.DeveloperNotFoundException;

public interface IBugDao {
	void addBug(Bug bug) throws SQLException;
	void assignBug(int bugId, int userId) throws SQLException;
	void closeBug(int bugId, int userId) throws SQLException;
	void markBugForClosing(int projectId, int bugId, int userId) throws SQLException;
	void updateBugStatus(int bugId, int status, int userId) throws SQLException;
	Bug getBugById(int bugId) throws SQLException;
	List<Bug> getBugsByTester(int userId) throws SQLException;
	List<Bug> getBugsByProject(int projectId) throws SQLException;
	List<Bug> getBugsByDeveloper(int userId) throws SQLException;
	List<Bug> getBugsForManager(int userId) throws SQLException, DeveloperNotFoundException;
}
