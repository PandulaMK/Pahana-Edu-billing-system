package com.pahanaedu.dao;

import com.pahanaedu.dao.DaoException;
import com.pahanaedu.model.User;

import java.util.Optional;

public interface UserDao {
    // existing
    User validateUser(String username, String password) throws DaoException;

    // new
    Optional<User> findByUsername(String username) throws DaoException;
    boolean existsByUsername(String username) throws DaoException;
    void create(User user) throws DaoException;
}
