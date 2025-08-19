package com.pahanaedu.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.pahanaedu.dao.UserDao;
import com.pahanaedu.dao.impl.UserDaoImpl;
import com.pahanaedu.model.User;

public class UserDaoTest {

    @Test
    void testCreateFindAndValidate() throws Exception {
        UserDao dao = new UserDaoImpl();

        User u = new User();
        u.setUsername("tester");
        u.setPassword("secret"); // match your DAO's validateUser behavior
        u.setRole("ADMIN");
        dao.create(u);

        assertTrue(dao.existsByUsername("tester"));

        Optional<User> byName = dao.findByUsername("tester");
        assertTrue(byName.isPresent());
        assertEquals("ADMIN", byName.get().getRole());

        User validated = dao.validateUser("tester", "secret");
        assertNotNull(validated);
        assertEquals("tester", validated.getUsername());
    }
}
