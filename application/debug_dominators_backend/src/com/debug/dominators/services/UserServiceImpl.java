package com.debug.dominators.services;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.debug.dominators.dao.IUserDao;
import com.debug.dominators.dao.UserDaoImpl;
import com.debug.dominators.model.Role;
import com.debug.dominators.model.User;
import com.debug.dominators.services.exceptions.InvalidUsernamePasswordException;
import com.debug.dominators.services.exceptions.RoleMismatchException;
import com.debug.dominators.services.exceptions.UserAlreadRegisteredException;
import com.debug.dominators.services.exceptions.UserNotFoundException;
import com.debug.dominators.services.exceptions.UserNotRegisteredException;
import com.debug.dominators.services.exceptions.WeakPasswordException;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    public UserServiceImpl() throws ClassNotFoundException, SQLException {
        userDao = UserDaoImpl.getUserDao();
    }

    /**
     * Adds a list of users to the system.
     *
     * @param users The list of users to add.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void addUsers(List<User> users) throws SQLException {
        userDao.addUsers(users.stream().filter(User::validate).collect(Collectors.toList()));
    }

    /**
     * Adds a user to the system.
     *
     * @param user The user to add.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
    }

    /**
     * Registers a user with the specified email, role, and password.
     *
     * @param email    The email of the user to register.
     * @param role     The role of the user to register.
     * @param password The password of the user to register.
     * @throws SQLException             if a database access error occurs.
     * @throws UserNotRegisteredException if the user is not found.
     * @throws UserAlreadyRegisteredException if the user is already registered.
     * @throws RoleMismatchException     if there is a role mismatch.
     * @throws WeakPasswordException     if the password is weak.
     */
    @Override
    public void registerUser(String email, String role, String password)
            throws SQLException, UserNotRegisteredException, UserAlreadRegisteredException, RoleMismatchException, WeakPasswordException {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            throw new UserNotRegisteredException();
        } else if (user.isRegistered()) {
            throw new UserAlreadRegisteredException();
        } else if (!Role.getRole(user.getTypeOfUserId()).equals(Role.getRole(user.getTypeOfUserId()))) {
            throw new RoleMismatchException();
        }
        user.setPassword(password);
        if (!user.validateWithPassword()) {
            throw new WeakPasswordException();
        }
        userDao.registerUser(email, role, password);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     * @throws SQLException        if a database access error occurs.
     * @throws UserNotFoundException if the user is not found.
     */
    @Override
    public User getUserById(int id) throws SQLException, UserNotFoundException {
        User user = userDao.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    /**
     * Logs in a user with the specified username and password.
     *
     * @param username The username of the user to log in.
     * @param password The password of the user to log in.
     * @return True if the login is successful, otherwise False.
     * @throws SQLException                   if a database access error occurs.
     * @throws InvalidUsernamePasswordException if the username or password is invalid.
     */
    @Override
    public boolean login(String username, String password) throws SQLException, InvalidUsernamePasswordException {
        if (userDao.getUserByUsernamePassword(username, password) == null) {
            throw new InvalidUsernamePasswordException();
        }
        return true;
    }
}
