package com.debug.dominators.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.debug.dominators.model.Project;
import com.debug.dominators.utils.DatabaseUtils;

public class ProjectDaoImpl implements IProjectDao {

	private Connection con;
	private static IProjectDao projectDao;

	private ProjectDaoImpl() throws ClassNotFoundException, SQLException {
		con = DatabaseUtils.getConnection();
	}

	public static IProjectDao getProjectDao() throws ClassNotFoundException, SQLException {
		if (projectDao == null) {
			projectDao = new ProjectDaoImpl();
		}
		return projectDao;
	}

	@Override
	public void addProject(Project project) throws SQLException {

		String insertQuery = "INSERT INTO Project (project_name, description, start_date,  status, project_manager_id) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);

		preparedStatement.setString(1, project.getProjectName());
		preparedStatement.setString(2, project.getDescription());
		preparedStatement.setTimestamp(3, project.getStartDate() != null ? new java.sql.Timestamp(project.getStartDate().getTime()):null);
		preparedStatement.setInt(4, project.getStatus());
		preparedStatement.setInt(5, project.getProjectManagerId());

		preparedStatement.executeUpdate();

	}

	@Override
	public List<Project> getProjectsForUserId(int userId) throws SQLException {
		List<Project> projects = new ArrayList<>();

		String selectQuery = "SELECT * FROM Project WHERE project_id IN (SELECT project_id FROM projectmember where user_id = ?)";
		PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		preparedStatement.setInt(1, userId);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Project project = new Project();
			project.setProjectId(resultSet.getInt("project_id"));
			project.setProjectName(resultSet.getString("project_name"));
			project.setDescription(resultSet.getString("description"));
			project.setStartDate(resultSet.getDate("start_date"));
			project.setEndDate(resultSet.getDate("end_date"));
			project.setStatus(resultSet.getInt("status"));
			project.setProjectManagerId(resultSet.getInt("project_manager_id"));
			projects.add(project);
		}

		return projects;
	}
}
