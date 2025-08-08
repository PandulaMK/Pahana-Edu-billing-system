package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.DaoException;
import com.pahanaedu.dao.UserDao;
import com.pahanaedu.model.User;
import com.pahanaedu.util.DBUtil;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final String SQL_VALIDATE =
            "SELECT username, password, role FROM `user` WHERE username = ? AND password = ?";

    @Override
    public User validateUser(String username, String password) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_VALIDATE)) {

            ps.setString(1, username);
            ps.setString(2, password); // hash here if you store hashed passwords

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;

                User u = new User();
                u.setUsername(rs.getString("username"));
                // If you don’t want to hold the password, you can skip setting it:
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (SQLException e) {
            throw new DaoException("Validate user failed", e);
        }
    }
}
