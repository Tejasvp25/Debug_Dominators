package com.debug.dominators.dao;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.ProjectMember;

public interface IProjectMemberDao {
	void addProjectMember(ProjectMember member) throws SQLException;
	List<ProjectMember> getProjectMemberByProjectId(int projectId) throws SQLException;
	int getTesterCountByProjectId(int projectId) throws SQLException;
	ProjectMember getTesterByProjectId(int id) throws SQLException;
	ProjectMember getManagerByProjectId(int id) throws SQLException;
	ProjectMember getManagerByUserId(int id) throws SQLException;
	ProjectMember getDeveloperByUserIdAndProject(int id, int projId) throws SQLException;
	ProjectMember getDeveloperByUserId(int id) throws SQLException;
	boolean isDeveloperForProject(int projectId) throws SQLException;
}
