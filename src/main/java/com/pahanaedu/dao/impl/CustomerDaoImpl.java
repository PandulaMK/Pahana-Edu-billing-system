package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.dao.DaoException;
import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl implements CustomerDao {

    private static final String INSERT_SQL =
            "INSERT INTO customer (account_no, name, address, phone, units) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE customer SET account_no = ?, name = ?, address = ?, phone = ?, units = ? WHERE customer_id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM customer WHERE customer_id = ?";

    private static final String FIND_BY_ID_SQL =
            "SELECT customer_id, account_no, name, address, phone, units FROM customer WHERE customer_id = ?";

    private static final String FIND_BY_ACC_SQL =
            "SELECT customer_id, account_no, name, address, phone, units FROM customer WHERE account_no = ?";

    private static final String FIND_ALL_SQL =
            "SELECT customer_id, account_no, name, address, phone, units FROM customer ORDER BY customer_id DESC";

    @Override
    public void create(Customer c) throws DaoException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getAccountNo());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getPhone());
            ps.setInt(5, c.getUnits());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setCustomerId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Create customer failed", e);
        }
    }

    @Override
    public void update(Customer c) throws DaoException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, c.getAccountNo());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getPhone());
            ps.setInt(5, c.getUnits());
            ps.setInt(6, c.getCustomerId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Update customer failed", e);
        }
    }

    @Override
    public void delete(int customerId) throws DaoException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Delete customer failed", e);
        }
    }

    @Override
    public Optional<Customer> findById(int customerId) throws DaoException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Find customer by ID failed", e);
        }
    }

    @Override
    public Optional<Customer> findByAccountNo(String accountNo) throws DaoException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_ACC_SQL)) {

            ps.setString(1, accountNo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Find customer by account no failed", e);
        }
    }

    @Override
    public List<Customer> findAll() throws DaoException {
        List<Customer> result = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Find all customers failed", e);
        }
    }

    private Customer mapRow(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("customer_id"));
        c.setAccountNo(rs.getString("account_no"));
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("address"));
        c.setPhone(rs.getString("phone"));
        c.setUnits(rs.getInt("units"));
        return c;
    }
}
