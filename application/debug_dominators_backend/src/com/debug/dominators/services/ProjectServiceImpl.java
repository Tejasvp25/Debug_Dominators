package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.dao.IProjectDao;
import com.debug.dominators.dao.IUserDao;
import com.debug.dominators.dao.ProjectDaoImpl;
import com.debug.dominators.dao.UserDaoImpl;
import com.debug.dominators.model.Project;
import com.debug.dominators.model.User;
import com.debug.dominators.services.exceptions.LimitExceedException;
import com.debug.dominators.services.exceptions.PermissionException;
import com.debug.dominators.services.exceptions.UserNotFoundException;

public class ProjectServiceImpl implements IProjectService {

    private IProjectDao projectDao;
    private IUserDao userDao;
        
    public ProjectServiceImpl() throws ClassNotFoundException, SQLException {
        projectDao = ProjectDaoImpl.getProjectDao();
        userDao = UserDaoImpl.getUserDao();
    }

    /**
     * Adds a project.
     *
     * @param project The project to add.
     * @throws SQLException          if a database access error occurs.
     * @throws PermissionException   if the current user doesn't have permission to add a project.
     * @throws LimitExceedException if the user has exceeded the project limit.
     * @throws UserNotFoundException if the project manager user is not found.
     */
    @Override
    public void addProject(Project project) throws SQLException, PermissionException, LimitExceedException, UserNotFoundException {
        System.out.println(project.getProjectManagerId());
    	User user = userDao.getUserById(project.getProjectManagerId());
        if (user == null) {
            throw new UserNotFoundException();
        } else if (user.getTypeOfUserId() != 3) {
            throw new PermissionException();
        }
        List<Project> projects = getProjectsForUserId(project.getProjectManagerId());
        if (projects.size() == 4) {
            throw new LimitExceedException();
        }
        projectDao.addProject(project);
    }

    /**
     * Retrieves a list of projects for a specific user.
     *
     * @param userId The ID of the user for which to retrieve projects.
     * @return A list of projects associated with the user.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Project> getProjectsForUserId(int userId) throws SQLException {
        return projectDao.getProjectsForUserId(userId);
    }
}
