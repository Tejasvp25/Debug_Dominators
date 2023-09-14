package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.Bug;
import com.debug.dominators.services.exceptions.BugNotFoundException;
import com.debug.dominators.services.exceptions.DeveloperNotFoundException;
import com.debug.dominators.services.exceptions.ManagerNotFoundException;
import com.debug.dominators.services.exceptions.NotMarkedForClosingException;
import com.debug.dominators.services.exceptions.PermissionException;
import com.debug.dominators.services.exceptions.TesterNotFoundException;
import com.debug.dominators.services.exceptions.UserNotFoundException;

public interface IBugService {
	void addBug(Bug bug)  throws SQLException, TesterNotFoundException, PermissionException;
	void assignBug(int bugId, int projectId, int userIdTo, int userIdBy) throws SQLException,  PermissionException, ManagerNotFoundException, UserNotFoundException;
	void closeBug(int bugId, int userId) throws SQLException, ManagerNotFoundException, PermissionException, BugNotFoundException, NotMarkedForClosingException;
	void updateBugStatus(int bugId, int status, int userId) throws SQLException, ManagerNotFoundException, PermissionException;
	List<Bug> getBugsByTester(int userId) throws SQLException;
	List<Bug> getBugsByProject(int projectId) throws SQLException, ManagerNotFoundException, PermissionException;
	void markBugForClosing(int projectId, int bugId, int userId) throws SQLException, PermissionException, DeveloperNotFoundException, BugNotFoundException;
	List<Bug> getBugsByDeveloper(int userId) throws SQLException, DeveloperNotFoundException;
	List<Bug> getBugsForManager(int userId) throws SQLException, DeveloperNotFoundException;
}
