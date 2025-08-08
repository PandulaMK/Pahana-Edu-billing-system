// ItemDao.java
package com.pahanaedu.dao;

import com.pahanaedu.model.Item;
import java.util.*;

public interface ItemDao {
    void create(Item i) throws DaoException;
    void update(Item i) throws DaoException;
    void delete(int itemId) throws DaoException;
    Optional<Item> findById(int itemId) throws DaoException;
    List<Item> findAll() throws DaoException;
}
