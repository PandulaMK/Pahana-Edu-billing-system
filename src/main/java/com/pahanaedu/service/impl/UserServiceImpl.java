package com.pahanaedu.service.impl;

import com.pahanaedu.dao.UserDao;
import com.pahanaedu.dao.DaoException;
import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    public UserServiceImpl(UserDao userDao) { this.userDao = userDao; }

    @Override
    public boolean authenticate(String username, String password) throws Exception {
        return login(username, password).isPresent();
    }

    @Override
    public Optional<User> login(String username, String password) throws Exception {
        try {
            return Optional.ofNullable(userDao.validateUser(username, password));
        } catch (DaoException e) {
            throw new Exception("Login failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void createCashier(String username, String rawPassword) throws Exception {
        if (username == null || username.trim().isEmpty())
            throw new IllegalArgumentException("Username is required");
        if (rawPassword == null || rawPassword.trim().isEmpty())
            throw new IllegalArgumentException("Password is required");

        try {
            if (userDao.existsByUsername(username.trim())) {
                throw new IllegalArgumentException("Username already exists");
            }
            User u = new User();
            u.setUsername(username.trim());
            u.setPassword(rawPassword); // TODO: hash later
            u.setRole("CASHIER");
            userDao.create(u);
        } catch (DaoException e) {
            throw new Exception("Create cashier failed: " + e.getMessage(), e);
        }
    }
}
