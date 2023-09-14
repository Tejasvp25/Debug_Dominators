package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.model.ProjectMember;
import com.debug.dominators.services.exceptions.PermissionException;
import com.debug.dominators.services.exceptions.TesterAlreadyExistsException;

public interface IProjectMemberService {
	void addProjectMember(ProjectMember member, int projectId) throws SQLException, TesterAlreadyExistsException, PermissionException;
	List<ProjectMember> getProjectMemberByProjectId(int projectId) throws SQLException;
	int getTesterCountByProjectId(int projectId) throws SQLException;
	ProjectMember getTesterByProjectId(int id) throws SQLException;
}
