package com.pahanaedu.dao.impl;

import com.pahanaedu.dao.DaoException;
import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.model.Item;
import com.pahanaedu.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDao {

    private static final String INSERT_SQL =
            "INSERT INTO item (item_name, price, quantity) VALUES (?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE item SET item_name = ?, price = ?, quantity = ? WHERE item_id = ?";

    private static final String DELETE_SQL =
            "DELETE FROM item WHERE item_id = ?";

    private static final String FIND_BY_ID_SQL =
            "SELECT item_id, item_name, price, quantity FROM item WHERE item_id = ?";

    private static final String FIND_ALL_SQL =
            "SELECT item_id, item_name, price, quantity FROM item ORDER BY item_id DESC";

    @Override
    public void create(Item i) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, i.getItemName());
            ps.setBigDecimal(2, i.getPrice());
            ps.setInt(3, i.getQuantity());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) i.setItemId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Create item failed", e);
        }
    }

    @Override
    public void update(Item i) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, i.getItemName());
            ps.setBigDecimal(2, i.getPrice());
            ps.setInt(3, i.getQuantity());
            ps.setInt(4, i.getItemId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Update item failed", e);
        }
    }

    @Override
    public void delete(int itemId) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SQL)) {

            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Delete item failed", e);
        }
    }

    @Override
    public Optional<Item> findById(int itemId) throws DaoException {
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {

            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("Find item by ID failed", e);
        }
    }

    @Override
    public List<Item> findAll() throws DaoException {
        List<Item> items = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(mapRow(rs));
            }
            return items;
        } catch (SQLException e) {
            throw new DaoException("Find all items failed", e);
        }
    }

    private Item mapRow(ResultSet rs) throws SQLException {
        Item i = new Item();
        i.setItemId(rs.getInt("item_id"));
        i.setItemName(rs.getString("item_name"));
        i.setPrice(rs.getBigDecimal("price"));
        i.setQuantity(rs.getInt("quantity"));
        return i;
        }
}
