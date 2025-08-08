package com.pahanaedu.dao;

import com.pahanaedu.model.User;

public interface IUserDAO {
    User validateUser(String username, String password);
}
