package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;

import com.debug.dominators.dao.BugDaoImpl;
import com.debug.dominators.dao.IBugDao;
import com.debug.dominators.dao.IProjectMemberDao;
import com.debug.dominators.dao.ProjectMemberDaoImpl;
import com.debug.dominators.model.Bug;
import com.debug.dominators.model.ProjectMember;
import com.debug.dominators.services.exceptions.BugNotFoundException;
import com.debug.dominators.services.exceptions.DeveloperNotFoundException;
import com.debug.dominators.services.exceptions.ManagerNotFoundException;
import com.debug.dominators.services.exceptions.NotMarkedForClosingException;
import com.debug.dominators.services.exceptions.PermissionException;
import com.debug.dominators.services.exceptions.TesterNotFoundException;
import com.debug.dominators.services.exceptions.UserNotFoundException;

public class BugServiceImpl implements IBugService {

    private IBugDao bugDao;
    private IProjectMemberDao projectMemberDao;

    public BugServiceImpl() throws ClassNotFoundException, SQLException {
        bugDao = BugDaoImpl.getBugDao();
        projectMemberDao = ProjectMemberDaoImpl.getProjectMemberDao();
    }

    /**
     * Adds a new bug to the system.
     *
     * @param bug The bug to add.
     * @throws SQLException              if a database access error occurs.
     * @throws TesterNotFoundException   if the tester is not found.
     * @throws PermissionException       if the user does not have permission to add the bug.
     */
    @Override
    public void addBug(Bug bug) throws SQLException, TesterNotFoundException, PermissionException {
        // Check if the user is a tester for the project and has permission to add a bug
        ProjectMember member = projectMemberDao.getTesterByProjectId(bug.getProjectId());
        if (member != null && member.getId() == bug.getCreatedById()) {
            bugDao.addBug(bug);
        } else if (member == null) {
            throw new TesterNotFoundException();
        } else {
            throw new PermissionException();
        }
    }

    /**
     * Assigns a bug to a developer for a specific project.
     *
     * @param bugId   The ID of the bug to assign.
     * @param projectId The ID of the project.
     * @param userIdTo  The ID of the user to assign the bug to.
     * @param userIdBy  The ID of the user assigning the bug.
     * @throws SQLException              if a database access error occurs.
     * @throws PermissionException       if the user does not have permission to assign the bug.
     * @throws ManagerNotFoundException if the manager is not found for the project.
     * @throws UserNotFoundException    if the user to be assigned is not found or is not a developer.
     */
    @Override
    public void assignBug(int bugId, int projectId, int userIdTo, int userIdBy)
            throws SQLException, PermissionException, ManagerNotFoundException, UserNotFoundException {
        // Check if the user is a manager for the project
        ProjectMember manager = projectMemberDao.getManagerByProjectId(projectId);
        // Check if the user to be assigned is a developer for the project
        ProjectMember dev = projectMemberDao.getDeveloperByUserIdAndProject(userIdTo, projectId);

        if (manager == null) {
            throw new ManagerNotFoundException();
        }
        if (dev == null) {
            throw new UserNotFoundException();
        } else if (manager.getId() == userIdBy) {
            bugDao.assignBug(bugId, userIdTo);
        } else {
            throw new PermissionException();
        }
    }

    /**
     * Closes a bug if it is marked for closing.
     *
     * @param bugId  The ID of the bug to close.
     * @param userId The ID of the user closing the bug.
     * @throws SQLException              if a database access error occurs.
     * @throws ManagerNotFoundException if the manager is not found for the project.
     * @throws PermissionException       if the user does not have permission to close the bug.
     * @throws BugNotFoundException      if the bug is not found.
     * @throws NotMarkedForClosingException if the bug is not marked for closing.
     */
    @Override
    public void closeBug(int bugId, int userId)
            throws SQLException, ManagerNotFoundException, PermissionException, BugNotFoundException, NotMarkedForClosingException {
        // Check if the user is a manager for the project
        ProjectMember manager = projectMemberDao.getManagerByProjectId(userId);
        Bug bug = bugDao.getBugById(bugId);

        if (manager == null) {
            throw new ManagerNotFoundException();
        } else if (bug == null) {
            throw new BugNotFoundException();
        } else if (manager.getId() == userId) {
            if (!bug.getMarkedForClosing()) {
                throw new NotMarkedForClosingException();
            }
            bugDao.closeBug(bugId, userId);
        } else {
            throw new PermissionException();
        }
    }

    // ...
    // Comments for the remaining methods have been added as well.
    // ...

    /**
     * Updates the status of a bug.
     *
     * @param bugId  The ID of the bug to update.
     * @param status The new status for the bug.
     * @param userId The ID of the user performing the update.
     * @throws SQLException              if a database access error occurs.
     * @throws ManagerNotFoundException if the manager is not found for the project.
     * @throws PermissionException       if the user does not have permission to update the bug.
     */
    @Override
    public void updateBugStatus(int bugId, int status, int userId)
            throws SQLException, ManagerNotFoundException, PermissionException {
        // Check if the user is a manager for the project
        ProjectMember manager = projectMemberDao.getManagerByUserId(userId);

        if (manager == null) {
            throw new ManagerNotFoundException();
        } else if (manager.getId() == userId) {
            bugDao.updateBugStatus(bugId, status, userId);
        } else {
            throw new PermissionException();
        }
    }

    /**
     * Retrieves a list of bugs associated with a specific tester.
     *
     * @param userId The ID of the tester.
     * @return A list of bugs associated with the tester.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Bug> getBugsByTester(int userId) throws SQLException {
        // Retrieve a list of bugs associated with the specified tester
        return bugDao.getBugsByTester(userId);
    }

    /**
     * Retrieves a list of bugs associated with a specific project.
     *
     * @param projectId The ID of the project.
     * @return A list of bugs associated with the project.
     * @throws SQLException              if a database access error occurs.
     * @throws ManagerNotFoundException if the manager is not found for the project.
     * @throws PermissionException       if the user does not have permission to retrieve the bugs.
     */
    @Override
    public List<Bug> getBugsByProject(int projectId)
            throws SQLException, ManagerNotFoundException, PermissionException {
        // Check if the user is a manager for the project
        ProjectMember manager = projectMemberDao.getManagerByProjectId(projectId);

        if (manager == null) {
            throw new ManagerNotFoundException();
        } else if (manager.getId() == projectId) {
            // Retrieve a list of bugs associated with the specified project
            return bugDao.getBugsByProject(projectId);
        } else {
            throw new PermissionException();
        }
    }

    /**
     * Marks a bug for closing by a developer.
     *
     * @param projectId The ID of the project.
     * @param bugId     The ID of the bug to mark for closing.
     * @param userId    The ID of the developer marking the bug.
     * @throws SQLException              if a database access error occurs.
     * @throws PermissionException       if the user does not have permission to mark the bug for closing.
     * @throws DeveloperNotFoundException if the developer is not found for the project.
     * @throws BugNotFoundException      if the bug is not found.
     */
    @Override
    public void markBugForClosing(int projectId, int bugId, int userId)
            throws SQLException, PermissionException, DeveloperNotFoundException, BugNotFoundException {
        // Check if the user is a developer for the project
        ProjectMember developer = projectMemberDao.getDeveloperByUserIdAndProject(userId, projectId);
        Bug bug = bugDao.getBugById(bugId);

        if (developer == null) {
            throw new DeveloperNotFoundException();
        } else if (bug == null) {
            throw new BugNotFoundException();
        } else if (developer.getId() == bug.getAssignedToId()) {
            bugDao.markBugForClosing(projectId, bugId, userId);
        } else {
            throw new PermissionException();
        }
    }

    /**
     * Retrieves a list of bugs assigned to a specific developer.
     *
     * @param userId The ID of the developer.
     * @return A list of bugs assigned to the developer.
     * @throws SQLException              if a database access error occurs.
     * @throws DeveloperNotFoundException if the developer is not found.
     */
    @Override
    public List<Bug> getBugsByDeveloper(int userId) throws SQLException, DeveloperNotFoundException {
        // Check if the user is a developer
        ProjectMember developer = projectMemberDao.getDeveloperByUserId(userId);

        if (developer == null) {
            throw new DeveloperNotFoundException();
        } else {
            // Retrieve a list of bugs assigned to the developer
            return bugDao.getBugsByDeveloper(userId);
        }
    }

    /**
     * Retrieves a list of bugs for a manager.
     *
     * @param userId The ID of the manager.
     * @return A list of bugs for the manager.
     * @throws SQLException if a database access error occurs.
     * @throws DeveloperNotFoundException 
     */
    @Override
    public List<Bug> getBugsForManager(int userId) throws SQLException, DeveloperNotFoundException {
        // Retrieve a list of bugs for the manager (no permission check required)
        return bugDao.getBugsForManager(userId);
    }
}


