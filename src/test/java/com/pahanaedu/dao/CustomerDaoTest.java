package com.pahanaedu.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.dao.impl.CustomerDaoImpl;
import com.pahanaedu.model.Customer;

public class CustomerDaoTest {

    @Test
    void testCreateFindUpdateDelete() throws Exception {
        CustomerDao dao = new CustomerDaoImpl();

        // create #1
        Customer c1 = new Customer();
        c1.setAccountNo("ACC_001");
        c1.setName("Alice");
        c1.setAddress("Colombo");
        c1.setPhone("0771234567");
        c1.setUnits(10);
        dao.create(c1);
        assertTrue(c1.getCustomerId() > 0);

        // create #2
        Customer c2 = new Customer();
        c2.setAccountNo("ACC_002");
        c2.setName("Bob");
        c2.setAddress("Kandy");
        c2.setPhone("0770000000");
        c2.setUnits(5);
        dao.create(c2);

        // list
        List<Customer> all = dao.findAll();
        assertFalse(all.isEmpty());

        // find one (Optional)
        Optional<Customer> gotOpt = dao.findByAccountNo("ACC_001");
        assertTrue(gotOpt.isPresent());
        Customer got = gotOpt.get();
        assertEquals("Alice", got.getName());

        // update
        got.setUnits(12);
        dao.update(got);
        Optional<Customer> afterOpt = dao.findByAccountNo("ACC_001");
        assertTrue(afterOpt.isPresent());
        assertEquals(12, afterOpt.get().getUnits());

        // delete by id
        dao.delete(afterOpt.get().getCustomerId());
        Optional<Customer> shouldBeEmpty = dao.findByAccountNo("ACC_001");
        assertTrue(!shouldBeEmpty.isPresent(), "Deleted customer must not be found");
    }
}
