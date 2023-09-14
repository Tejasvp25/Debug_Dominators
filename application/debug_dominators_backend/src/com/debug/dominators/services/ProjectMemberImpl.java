package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.dao.IProjectMemberDao;
import com.debug.dominators.dao.ProjectMemberDaoImpl;
import com.debug.dominators.model.ProjectMember;
import com.debug.dominators.services.exceptions.PermissionException;
import com.debug.dominators.services.exceptions.TesterAlreadyExistsException;

public class ProjectMemberImpl implements IProjectMemberService {

    private IProjectMemberDao projectDao;

    public ProjectMemberImpl() throws ClassNotFoundException, SQLException {
        projectDao = ProjectMemberDaoImpl.getProjectMemberDao();
    }

    /**
     * Adds a project member to a project.
     *
     * @param member    The project member to add.
     * @param projectId The ID of the project to which the member should be added.
     * @throws SQLException                if a database access error occurs.
     * @throws TesterAlreadyExistsException if a tester with the same project already exists.
     * @throws PermissionException         if the current user doesn't have permission to add a member.
     */
    @Override
    public void addProjectMember(ProjectMember member, int projectId)
            throws SQLException, TesterAlreadyExistsException, PermissionException {
        ProjectMember manager = projectDao.getManagerByProjectId(projectId);

        if (manager == null && member.getTypeOfUserId() != 3) {
            throw new PermissionException();
        }

        if (member.getTypeOfUserId() == 2) {
            if (projectDao.getTesterCountByProjectId(member.getProjectId()) == 0) {
                projectDao.addProjectMember(member);
            } else {
                throw new TesterAlreadyExistsException();
            }
        } else {
            projectDao.addProjectMember(member);
        }
    }

    /**
     * Retrieves a list of project members for a specific project.
     *
     * @param projectId The ID of the project for which to retrieve members.
     * @return A list of project members.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<ProjectMember> getProjectMemberByProjectId(int projectId) throws SQLException {
        return projectDao.getProjectMemberByProjectId(projectId);
    }

    /**
     * Retrieves the number of testers in a specific project.
     *
     * @param projectId The ID of the project for which to count testers.
     * @return The number of testers in the project.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public int getTesterCountByProjectId(int projectId) throws SQLException {
        return projectDao.getTesterCountByProjectId(projectId);
    }

    /**
     * Retrieves a tester for a specific project.
     *
     * @param id The ID of the project for which to retrieve a tester.
     * @return The project member who is a tester for the project.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public ProjectMember getTesterByProjectId(int id) throws SQLException {
        return projectDao.getTesterByProjectId(id);
    }
}
