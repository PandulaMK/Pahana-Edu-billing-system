package com.pahanaedu.dao;

import com.pahanaedu.dao.DaoException;
import com.pahanaedu.model.User;

public interface UserDao {
    // returns User if valid, otherwise null
    User validateUser(String username, String password) throws DaoException;
}
