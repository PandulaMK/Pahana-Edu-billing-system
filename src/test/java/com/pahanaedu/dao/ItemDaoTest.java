package com.pahanaedu.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.dao.impl.ItemDaoImpl;
import com.pahanaedu.model.Item;

public class ItemDaoTest {

    @Test
    void testCreateUpdateFindList() throws Exception {
        ItemDao dao = new ItemDaoImpl();

        Item i = new Item();
        i.setItemName("Book A");
        i.setPrice(new BigDecimal("500.00"));
        i.setQuantity(20);
        dao.create(i);
        assertTrue(i.getItemId() > 0);

        Optional<Item> fetched = dao.findById(i.getItemId());
        assertTrue(fetched.isPresent());
        assertEquals("Book A", fetched.get().getItemName());

        // update
        i.setPrice(new BigDecimal("650.00"));
        i.setQuantity(15);
        dao.update(i);

        Optional<Item> after = dao.findById(i.getItemId());
        assertTrue(after.isPresent());
        assertEquals(new BigDecimal("650.00"), after.get().getPrice());
        assertEquals(15, after.get().getQuantity());

        List<Item> all = dao.findAll();
        assertFalse(all.isEmpty());
    }
}
