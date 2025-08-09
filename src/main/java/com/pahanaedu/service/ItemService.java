package com.pahanaedu.service;

import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.dao.DaoException;
import com.pahanaedu.dao.impl.ItemDaoImpl;
import com.pahanaedu.model.Item;

import java.util.List;
import java.util.Optional;

public class ItemService {

    private final ItemDao itemDao;

    public ItemService() {
        this.itemDao = new ItemDaoImpl();
    }

    public void addItem(Item item) throws DaoException {
        itemDao.create(item);
    }

    public void updateItem(Item item) throws DaoException {
        itemDao.update(item);
    }

    public void deleteItem(int itemId) throws DaoException {
        itemDao.delete(itemId);
    }

    public Optional<Item> getItemById(int itemId) throws DaoException {
        return itemDao.findById(itemId);
    }

    public List<Item> getAllItems() throws DaoException {
        return itemDao.findAll();
    }
}
