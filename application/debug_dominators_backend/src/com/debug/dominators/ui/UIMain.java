package com.debug.dominators.ui;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.debug.dominators.dao.*;
import com.debug.dominators.model.*;
import com.debug.dominators.services.*;
import com.debug.dominators.services.exceptions.*;


public class UIMain {

    public static void main(String[] args) {
    	try {
        	// Create and add roles
        	IRoleService roleService = new RoleServiceImpl();
        	Role testerRole = new Role(2,"tester");
        	Role managerRole = new Role(3,"Manager");
        	Role developerRole = new Role(1,"Developer");
        	roleService.addRole(testerRole);
        	roleService.addRole(managerRole);
        	roleService.addRole(developerRole);

        	// Create and register users
        	IUserService userService = new UserServiceImpl();
            // Creating user instances
            User tester = new User(1, "Tester", "tester@example.com", 2, false, null);
            User manager = new User(2, "Manager", "manager@example.com", 3, "Manager123", false, null, null, null);
            User developer = new User(3, "Developer", "developer@example.com", 1, "Developer123", false, null, null, null);

//        	userService.addUsers(Arrays.asList(tester, manager, developer));

        	// Authenticate users
        	AuthServiceImpl authService = new AuthServiceImpl(new AuthDaoImpl());
        	authService.authenticate(tester.getUserId());
        	authService.authenticate(manager.getUserId());
        	authService.authenticate(developer.getUserId());

        	// Create and manage projects
        	IProjectService projectService = new ProjectServiceImpl();
            // Creating Project instances
            Project project1 = new Project(1, "Project 1", "Description 1", null, null, 0, manager.getUserId());
            Project project2 = new Project(2, "Project 2", "Description 2", null, null, 0, manager.getUserId());
//        	projectService.addProject(project1);
//        	projectService.addProject(project2);

        	// Add project members
        	IProjectMemberService projectMemberService = new ProjectMemberImpl();
            // Create ProjectMember instances
            ProjectMember projectManager1 = new ProjectMember(1, project1.getProjectId(), manager.getUserId(), 3); // Manager
            ProjectMember projectManager2 = new ProjectMember(2, project2.getProjectId(), manager.getUserId(), 3); // Manager
            ProjectMember tester1 = new ProjectMember(3, project1.getProjectId(), developer.getUserId(), 2); // Tester
            ProjectMember tester2 = new ProjectMember(4, project2.getProjectId(), developer.getUserId(), 2); // Tester

            // Add project members
//            projectMemberService.addProjectMember(projectManager1, project1.getProjectId());
//            projectMemberService.addProjectMember(projectManager2, project2.getProjectId());
//            projectMemberService.addProjectMember(tester1, project1.getProjectId());
//            projectMemberService.addProjectMember(tester2, project2.getProjectId());

        	// Create and manage bugs
        	IBugService bugService = new BugServiceImpl();
            // Create Bug instances
            Bug bug1 = new Bug(1, "Bug 1", "Description 1", project1.getProjectId(), tester1.getId(), null,
                    developer.getUserId(), false, 0, null, 0, 1);

            Bug bug2 = new Bug(2, "Bug 2", "Description 2", project2.getProjectId(), tester2.getId(), null,
                    developer.getUserId(), false, 0, null, 0, 1);

            // Add bugs
//            bugService.addBug(bug1);
//            bugService.addBug(bug2);

            // Assign bugs to developers
            bugService.assignBug(bug1.getId(), project1.getProjectId(), developer.getUserId(), manager.getUserId());
            bugService.assignBug(bug2.getId(), project2.getProjectId(), developer.getUserId(), manager.getUserId());

            // Mark bugs for closing
            bugService.markBugForClosing(project1.getProjectId(), bug1.getId(), developer.getUserId());
            bugService.markBugForClosing(project2.getProjectId(), bug2.getId(), developer.getUserId());

            // Close bugs
            bugService.closeBug(bug1.getId(), developer.getUserId());
            bugService.closeBug(bug2.getId(), developer.getUserId());

            // Get bugs by various criteria
            List<Bug> bugsByTester1 = bugService.getBugsByTester(tester1.getId());
            List<Bug> bugsByProject1 = bugService.getBugsByProject(project1.getProjectId());
            List<Bug> bugsByDeveloper = bugService.getBugsByDeveloper(developer.getUserId());
            List<Bug> bugsForManager = bugService.getBugsForManager(manager.getUserId());

            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        try {
        	IUserService userService = new UserServiceImpl();
            // Attempt to register a user with a weak password
            userService.registerUser("weakuser@example.com", "Developer", "123");
        } catch (WeakPasswordException | SQLException | UserNotRegisteredException | UserAlreadRegisteredException | RoleMismatchException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
        	IUserService userService = new UserServiceImpl();
            // Attempt to register a user with a role mismatch
            userService.registerUser("mismatched@example.com", "Admin", "StrongPassword123");
        } catch (RoleMismatchException | ClassNotFoundException | SQLException | UserNotRegisteredException | UserAlreadRegisteredException | WeakPasswordException e) {
        	e.printStackTrace();
        }

        try {
        	IProjectService projectService = new ProjectServiceImpl();
            // Attempt to add more than 4 projects for a project manager
            for (int i = 3; i < 8; i++) {
                Project project = new Project( 2);
                projectService.addProject(project);
            }
        } catch (LimitExceedException | SQLException | PermissionException | UserNotFoundException | ClassNotFoundException e) {
        	e.printStackTrace();
        }

        try {
        	IProjectMemberService projectMemberService = new ProjectMemberImpl();
            // Attempt to add a duplicate tester to a project
            ProjectMember duplicateTester = new ProjectMember(1,3, 1, 2);
            projectMemberService.addProjectMember(duplicateTester, 1);
        } catch (TesterAlreadyExistsException | SQLException | PermissionException | ClassNotFoundException e) {
        	e.printStackTrace();
        }

        try {
        	IBugService bugService = new BugServiceImpl();
            // Attempt to assign a bug to a user who is not a developer
            bugService.assignBug(1, 1, 2, 2);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
        	IBugService bugService = new BugServiceImpl();
            // Attempt to close a bug that is not marked for closing
            bugService.closeBug(1, 3);
        } catch (NotMarkedForClosingException | SQLException | ManagerNotFoundException | PermissionException | BugNotFoundException e) {
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
}
