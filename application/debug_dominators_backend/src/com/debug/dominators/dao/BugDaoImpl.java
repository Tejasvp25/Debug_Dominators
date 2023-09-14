package com.debug.dominators.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.debug.dominators.model.Bug;
import com.debug.dominators.services.exceptions.DeveloperNotFoundException;
import com.debug.dominators.utils.DatabaseUtils;

public class BugDaoImpl implements IBugDao {

    private Connection con;
    private static IBugDao bugDao;
    
    private BugDaoImpl() throws ClassNotFoundException, SQLException {
        con = DatabaseUtils.getConnection();
    }
    
    public static IBugDao getBugDao() throws ClassNotFoundException, SQLException {
    	if(bugDao == null) {
    		bugDao = new BugDaoImpl();
    	}
    	return bugDao;
    }

    @Override
    public void addBug(Bug bug) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO bug (title, description, project_id, created_by_id, open_date, assigned_to_id, marked_for_closing, closed_by_id, closed_on, status, severity_level_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, bug.getTitle());
        ps.setString(2, bug.getDescription());
        ps.setInt(3, bug.getProjectId());
        ps.setInt(4, bug.getCreatedById());
        ps.setTimestamp(5, bug.getOpenDate() != null ? new java.sql.Timestamp(bug.getOpenDate().getTime()) : null);
        ps.setInt(6, bug.getAssignedToId());
        ps.setBoolean(7, bug.getMarkedForClosing());
        ps.setInt(8, bug.getClosedById());
        if (bug.getClosedOn() != null) {
            ps.setTimestamp(9, new java.sql.Timestamp(bug.getClosedOn().getTime()));
        } else {
            ps.setTimestamp(9, null);
        }
        ps.setInt(10, bug.getStatus());
        ps.setInt(11, bug.getSeverityLevelId());
        ps.executeUpdate();
    }

    @Override
    public void assignBug(int bugId, int userId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE bug SET assigned_to_id = ? WHERE id = ?");
        ps.setInt(1, userId);
        ps.setInt(2, bugId);
        ps.executeUpdate();
    }

    @Override
    public void closeBug(int bugId, int userId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE bug SET closed_by_id = ?, closed_on = CURRENT_TIMESTAMP, marked_for_closing = false WHERE id = ?");
        ps.setInt(1, userId);
        ps.setInt(2, bugId);
        ps.executeUpdate();
    }

    @Override
    public void updateBugStatus(int bugId, int status, int userId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE bug SET status = ? WHERE id = ?");
        ps.setInt(1, status);
        ps.setInt(2, bugId);
        ps.executeUpdate();
    }

    @Override
    public List<Bug> getBugsByTester(int userId) throws SQLException {
        List<Bug> bugs = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM bug WHERE assigned_to_id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Bug bug = new Bug(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("project_id"),
                rs.getInt("created_by_id"),
                new Date(rs.getTimestamp("open_date").getTime()),
                rs.getInt("assigned_to_id"),
                rs.getBoolean("marked_for_closing"),
                rs.getInt("closed_by_id"),
                rs.getTimestamp("closed_on") != null ? new Date(rs.getTimestamp("closed_on").getTime()) : null,
                rs.getInt("status"),
                rs.getInt("severity_level_id")
            );
            bugs.add(bug);
        }

        return bugs;
    }

	@Override
	public List<Bug> getBugsByProject(int projectId) throws SQLException {
        List<Bug> bugs = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM bug WHERE project_id = ?");
        ps.setInt(1, projectId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Bug bug = new Bug(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("project_id"),
                rs.getInt("created_by_id"),
                new Date(rs.getTimestamp("open_date").getTime()),
                rs.getInt("assigned_to_id"),
                rs.getBoolean("marked_for_closing"),
                rs.getInt("closed_by_id"),
                rs.getTimestamp("closed_on") != null ? new Date(rs.getTimestamp("closed_on").getTime()) : null,
                rs.getInt("status"),
                rs.getInt("severity_level_id")
            );
            bugs.add(bug);
        }

        return bugs;
	}

	@Override
	public void markBugForClosing(int projectId, int bugId, int userId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE bug SET marked_for_closing = false WHERE id = ? and assigned_to_id = ? and project_id = ?");
        ps.setInt(1, bugId);
        ps.setInt(2, userId);
        ps.setInt(3, projectId);
        ps.executeUpdate();
	}

	@Override
	public List<Bug> getBugsByDeveloper(int userId) throws SQLException {
        List<Bug> bugs = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM bug WHERE assigned_to_id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Bug bug = new Bug(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("project_id"),
                rs.getInt("created_by_id"),
                new Date(rs.getTimestamp("open_date").getTime()),
                rs.getInt("assigned_to_id"),
                rs.getBoolean("marked_for_closing"),
                rs.getInt("closed_by_id"),
                rs.getTimestamp("closed_on") != null ? new Date(rs.getTimestamp("closed_on").getTime()) : null,
                rs.getInt("status"),
                rs.getInt("severity_level_id")
            );
            bugs.add(bug);
        }

        return bugs;
	}

	@Override
	public Bug getBugById(int bugId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM bug WHERE created_by_id = ?");
        ps.setInt(1, bugId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Bug bug = new Bug(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("project_id"),
                rs.getInt("created_by_id"),
                new Date(rs.getTimestamp("open_date").getTime()),
                rs.getInt("assigned_to_id"),
                rs.getBoolean("marked_for_closing"),
                rs.getInt("closed_by_id"),
                rs.getTimestamp("closed_on") != null ? new Date(rs.getTimestamp("closed_on").getTime()) : null,
                rs.getInt("status"),
                rs.getInt("severity_level_id")
            );
            return bug;
        }

        return null;
	}

	@Override
	public List<Bug> getBugsForManager(int userId) throws SQLException, DeveloperNotFoundException {
        List<Bug> bugs = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM bug WHERE project_id in (SELECT project_id FROM project where project_manager_id = ? )");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Bug bug = new Bug(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("project_id"),
                rs.getInt("created_by_id"),
                new Date(rs.getTimestamp("open_date").getTime()),
                rs.getInt("assigned_to_id"),
                rs.getBoolean("marked_for_closing"),
                rs.getInt("closed_by_id"),
                rs.getTimestamp("closed_on") != null ? new Date(rs.getTimestamp("closed_on").getTime()) : null,
                rs.getInt("status"),
                rs.getInt("severity_level_id")
            );
            bugs.add(bug);
        }

        return bugs;
	}

}
