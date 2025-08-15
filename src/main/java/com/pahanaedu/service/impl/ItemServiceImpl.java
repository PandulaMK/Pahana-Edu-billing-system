package com.pahanaedu.service.impl;

import com.pahanaedu.dao.DaoException;
import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;

import java.util.List;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public void addItem(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (item.getItemName() == null || item.getItemName().isEmpty())
            throw new IllegalArgumentException("Item name is required");
        try {
            itemDao.create(item);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to add item", e);
        }
    }

    @Override
    public void updateItem(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        try {
            itemDao.update(item);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to update item", e);
        }
    }

    @Override
    public void deleteItem(int itemId) {
        if (itemId <= 0) throw new IllegalArgumentException("Invalid item id");
        try {
            itemDao.delete(itemId);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to delete item", e);
        }
    }

    @Override
    public Optional<Item> getItemById(int itemId) {
        try {
            return itemDao.findById(itemId);
        } catch (DaoException e) {
            throw new RuntimeException("Failed to find item", e);
        }
    }

    @Override
    public List<Item> getAllItems() {
        try {
            return itemDao.findAll();
        } catch (DaoException e) {
            throw new RuntimeException("Failed to list items", e);
        }
    }
}
