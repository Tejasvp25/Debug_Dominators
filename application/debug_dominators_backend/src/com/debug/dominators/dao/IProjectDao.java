package com.debug.dominators.dao;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.Project;

public interface IProjectDao {
	void addProject(Project project) throws SQLException ;
	List<Project> getProjectsForUserId(int userId) throws SQLException ;
}
