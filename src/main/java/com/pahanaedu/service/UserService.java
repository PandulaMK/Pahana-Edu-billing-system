package com.pahanaedu.service;

import com.pahanaedu.dao.UserDao;
import com.pahanaedu.dao.impl.UserDaoImpl;  // or use your DAOFactory if preferred
import com.pahanaedu.model.User;

public class UserService {
    private final UserDao userDAO = new UserDaoImpl();

    public boolean authenticate(String username, String password) throws Exception {
        User user = userDAO.validateUser(username, password);
        return user != null;
    }
}
