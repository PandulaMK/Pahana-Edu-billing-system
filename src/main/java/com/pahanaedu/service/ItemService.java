package com.pahanaedu.service;

import com.pahanaedu.dao.IItemDAO;
import com.pahanaedu.factory.DAOFactory;
import com.pahanaedu.model.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemService {
    private final IItemDAO itemDAO;

    public ItemService() {
        this.itemDAO = DAOFactory.getItemDAO();
    }

    public void addItem(Item item) throws SQLException {
        itemDAO.addItem(item);
    }

    public void updateItem(Item item) throws SQLException {
        itemDAO.updateItem(item);
    }

    public void deleteItem(int id) throws SQLException {
        itemDAO.deleteItem(id);
    }

    public Item getItemById(int id) throws SQLException {
        return itemDAO.getItemById(id);
    }

    public List<Item> getAllItems() throws SQLException {
        return itemDAO.getAllItems();
    }
}
