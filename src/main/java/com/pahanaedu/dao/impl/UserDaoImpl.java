package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.DaoException;
import com.pahanaedu.dao.UserDao;
import com.pahanaedu.model.User;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    // If your table is `users`, change `user` -> `users` in all SQL below
    private static final String SQL_VALIDATE =
            "SELECT username, password, role FROM `user` WHERE username=? AND password=?";
    private static final String SQL_FIND_BY_USERNAME =
            "SELECT username, password, role FROM `user` WHERE username=?";
    private static final String SQL_EXISTS =
            "SELECT 1 FROM `user` WHERE username=?";
    private static final String SQL_INSERT =
            "INSERT INTO `user` (username, password, role) VALUES (?, ?, ?)";

    private Connection getConnection() throws SQLException {
        return DBUtil.getConnection();
    }

    @Override
    public User validateUser(String username, String password) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_VALIDATE)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (SQLException e) {
            throw new DaoException("Validate user failed", e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_USERNAME)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return Optional.of(u);
            }
        } catch (SQLException e) {
            throw new DaoException("findByUsername failed", e);
        }
    }

    @Override
    public boolean existsByUsername(String username) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_EXISTS)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new DaoException("existsByUsername failed", e);
        }
    }

    @Override
    public void create(User user) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // TODO: hash later
            ps.setString(3, user.getRole());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException dup) {
            throw new DaoException("Username already exists", dup);
        } catch (SQLException e) {
            throw new DaoException("Create user failed: " + e.getMessage(), e);
        }
    }
}
