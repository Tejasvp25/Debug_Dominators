package com.debug.dominators.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.debug.dominators.model.ProjectMember;
import com.debug.dominators.utils.DatabaseUtils;

public class ProjectMemberDaoImpl implements IProjectMemberDao {

	private Connection con;
	private static IProjectMemberDao projectMemberDao;

	private ProjectMemberDaoImpl() throws ClassNotFoundException, SQLException {
		con = DatabaseUtils.getConnection();
	}

	public static IProjectMemberDao getProjectMemberDao() throws ClassNotFoundException, SQLException {
		if (projectMemberDao == null) {
			projectMemberDao = new ProjectMemberDaoImpl();
		}
		return projectMemberDao;
	}

	@Override
	public void addProjectMember(ProjectMember member) throws SQLException {

		String insertQuery = "INSERT INTO ProjectMember (project_id, user_id, type_of_user_id) VALUES (?, ?, ?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

		preparedStatement.setInt(1, member.getProjectId());
		preparedStatement.setInt(2, member.getUserId());
		preparedStatement.setInt(3, member.getTypeOfUserId());

		preparedStatement.executeUpdate();

	}

	@Override
	public List<ProjectMember> getProjectMemberByProjectId(int projectId) throws SQLException {
		List<ProjectMember> projectMembers = new ArrayList<>();

		String selectQuery = "SELECT * FROM ProjectMember WHERE project_id = ?";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, projectId);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ProjectMember member = new ProjectMember();
			member.setId(resultSet.getInt("id"));
			member.setProjectId(resultSet.getInt("project_id"));
			member.setUserId(resultSet.getInt("user_id"));
			member.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			projectMembers.add(member);
		}

		return projectMembers;
	}

	@Override
	public int getTesterCountByProjectId(int projectId) throws SQLException {
			int testerCount = 0;
	        String sql = "SELECT COUNT(*) FROM user u " +
	                     "INNER JOIN projectmember pm ON u.type_of_user_id = pm.type_of_user_id " +
	                     "WHERE pm.project_id = ? AND u.type_of_user_id = ?";

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setInt(1, projectId);
	            preparedStatement.setInt(2, 2);
	            
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    testerCount = resultSet.getInt(1);
	                }
	            }
	        }

	        return testerCount;
	}

	@Override
	public ProjectMember getTesterByProjectId(int id) throws SQLException {
		String selectQuery = "SELECT * FROM ProjectMember WHERE project_id = ? and type_of_user_id = 2";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ProjectMember member = new ProjectMember();
			member.setId(resultSet.getInt("id"));
			member.setProjectId(resultSet.getInt("project_id"));
			member.setUserId(resultSet.getInt("user_id"));
			member.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			return member;
		}

		return null;
	}

	@Override
	public ProjectMember getManagerByProjectId(int id) throws SQLException {
		String selectQuery = "SELECT * FROM ProjectMember WHERE project_id = ? and type_of_user_id = 3";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ProjectMember member = new ProjectMember();
			member.setId(resultSet.getInt("id"));
			member.setProjectId(resultSet.getInt("project_id"));
			member.setUserId(resultSet.getInt("user_id"));
			member.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			return member;
		}

		return null;
	}

	@Override
	public ProjectMember getManagerByUserId(int id) throws SQLException {
		String selectQuery = "SELECT * FROM ProjectMember WHERE user_id = ? and type_of_user_id = 3";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ProjectMember member = new ProjectMember();
			member.setId(resultSet.getInt("id"));
			member.setProjectId(resultSet.getInt("project_id"));
			member.setUserId(resultSet.getInt("user_id"));
			member.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			return member;
		}

		return null;
	}

	@Override
	public ProjectMember getDeveloperByUserIdAndProject(int id, int projId) throws SQLException {
		String selectQuery = "SELECT * FROM ProjectMember WHERE user_id = ? and project_id = ? and type_of_user_id = 1";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, id);
		preparedStatement.setInt(2, projId);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ProjectMember member = new ProjectMember();
			member.setId(resultSet.getInt("id"));
			member.setProjectId(resultSet.getInt("project_id"));
			member.setUserId(resultSet.getInt("user_id"));
			member.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			return member;
		}

		return null;
	}
	
	@Override
	public ProjectMember getDeveloperByUserId(int id) throws SQLException {
		String selectQuery = "SELECT * FROM ProjectMember WHERE user_id = ? and type_of_user_id = 1";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ProjectMember member = new ProjectMember();
			member.setId(resultSet.getInt("id"));
			member.setProjectId(resultSet.getInt("project_id"));
			member.setUserId(resultSet.getInt("user_id"));
			member.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			return member;
		}

		return null;
	}

	@Override
	public boolean isDeveloperForProject(int id) throws SQLException {
		String selectQuery = "SELECT * FROM ProjectMember WHERE project_id = ? and type_of_user_id = 3";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ProjectMember member = new ProjectMember();
			member.setId(resultSet.getInt("id"));
			member.setProjectId(resultSet.getInt("project_id"));
			member.setUserId(resultSet.getInt("user_id"));
			member.setTypeOfUserId(resultSet.getInt("type_of_user_id"));
			return true;
		}

		return false;
	}
}
