package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.Project;
import com.debug.dominators.services.exceptions.LimitExceedException;
import com.debug.dominators.services.exceptions.PermissionException;
import com.debug.dominators.services.exceptions.UserNotFoundException;

public interface IProjectService {
	void addProject(Project project) throws SQLException, PermissionException, LimitExceedException, UserNotFoundException;
	List<Project> getProjectsForUserId(int userId) throws SQLException;
}
