package com.pahanaedu.service;

import com.pahanaedu.dao.IUserDAO;
import com.pahanaedu.factory.DAOFactory;
import com.pahanaedu.model.User;

import java.sql.SQLException;

public class UserService {
    private final IUserDAO userDAO;

    public UserService() {
        this.userDAO = DAOFactory.getUserDAO();
    }

    public User validateLogin(String username, String password) throws SQLException {
        return userDAO.validateUser(username, password);
    }
}
