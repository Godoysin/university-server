package com.luxoft.producer.db.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    @Test
    public void createUserTest() {
        String name = "testName";
        String password = "testPassword";

        User user = new User(name, password);

        assertNull(user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void createCompleteUserTest() {
        Long id = 1L;
        String name = "testName";
        String password = "testPassword";

        User user = new User(id, name, password);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void createEmptyUserTest() {
        User user = new User();

        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getPassword());
    }

}
