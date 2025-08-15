package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.BillDao;
import com.pahanaedu.dao.DaoException;
import com.pahanaedu.model.Bill;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BillDaoImpl implements BillDao {

	private static final String INSERT_SQL =
		    "INSERT INTO bill (bill_number, customer_id, units_consumed, rate_per_unit, total_amount, bill_date) " +
		    "VALUES (?, ?, ?, ?, ?, ?)";

		private static final String FIND_BY_ID_SQL =
		    "SELECT bill_id, bill_number, customer_id, units_consumed, rate_per_unit, total_amount, bill_date " +
		    "FROM bill WHERE bill_id = ?";

		private static final String FIND_BY_NUM_SQL =
		    "SELECT bill_id, bill_number, customer_id, units_consumed, rate_per_unit, total_amount, bill_date " +
		    "FROM bill WHERE bill_number = ?";

		private static final String FIND_BY_CUSTOMER_SQL =
		    "SELECT bill_id, bill_number, customer_id, units_consumed, rate_per_unit, total_amount, bill_date " +
		    "FROM bill WHERE customer_id = ? ORDER BY bill_date DESC";

		private static final String FIND_ALL_SQL =
		    "SELECT bill_id, bill_number, customer_id, units_consumed, rate_per_unit, total_amount, bill_date " +
		    "FROM bill ORDER BY bill_date DESC";

		private static final String NEXT_SEQ_FOR_YEAR_SQL =
		    "SELECT COALESCE(COUNT(*), 0) + 1 AS next_seq " +
		    "FROM bill " +
		    "WHERE YEAR(bill_date) = ?";

    @Override
    public void create(Bill bill) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, bill.getBillNumber());
            ps.setInt(2, bill.getCustomerId());
            ps.setInt(3, bill.getUnitsConsumed());
            ps.setBigDecimal(4, bill.getRatePerUnit());
            ps.setBigDecimal(5, bill.getTotalAmount());
            ps.setTimestamp(6, Timestamp.valueOf(bill.getBillDate()));

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) bill.setBillId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Create bill failed", e);
        }
    }

    @Override
    public Optional<Bill> findById(int billId) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, billId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Find bill by ID failed", e);
        }
    }

    @Override
    public Optional<Bill> findByNumber(String billNumber) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_NUM_SQL)) {

            ps.setString(1, billNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Find bill by number failed", e);
        }
    }

    @Override
    public List<Bill> findByCustomer(int customerId) throws DaoException {
        List<Bill> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_CUSTOMER_SQL)) {

            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new DaoException("Find bills by customer failed", e);
        }
    }

    @Override
    public List<Bill> findAll() throws DaoException {
        List<Bill> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
            return list;
        } catch (SQLException e) {
            throw new DaoException("Find all bills failed", e);
        }
    }

    @Override
    public int nextSequenceForYear(int year) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(NEXT_SEQ_FOR_YEAR_SQL)) {

            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("next_seq");
                return 1;
            }
        } catch (SQLException e) {
            throw new DaoException("Get next sequence for year failed", e);
        }
    }

    private Bill mapRow(ResultSet rs) throws SQLException {
        Bill b = new Bill();
        b.setBillId(rs.getInt("bill_id"));
        b.setBillNumber(rs.getString("bill_number"));
        b.setCustomerId(rs.getInt("customer_id"));
        b.setUnitsConsumed(rs.getInt("units_consumed"));
        b.setRatePerUnit(rs.getBigDecimal("rate_per_unit"));
        b.setTotalAmount(rs.getBigDecimal("total_amount"));
        Timestamp ts = rs.getTimestamp("bill_date");
        b.setBillDate(ts != null ? ts.toLocalDateTime() : null);

        return b;
    }
}
