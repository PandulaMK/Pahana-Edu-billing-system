package com.pahanaedu.service;

import com.pahanaedu.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    void addItem(Item item);
    void updateItem(Item item);
    void deleteItem(int itemId);
    Optional<Item> getItemById(int itemId);
    List<Item> getAllItems();
}
