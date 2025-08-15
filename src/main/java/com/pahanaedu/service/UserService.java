package com.pahanaedu.service;

import com.pahanaedu.model.User;
import java.util.Optional;

public interface UserService {
    boolean authenticate(String username, String password) throws Exception;

    // used by LoginServlet to branch by role
    Optional<User> login(String username, String password) throws Exception;

    // used by AddCashierServlet (admin-only)
    void createCashier(String username, String rawPassword) throws Exception;
}
