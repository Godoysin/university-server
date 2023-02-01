package com.luxoft.producer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    @Test
    public void createUserTest() {
        String name = "test";
        String password = "test";

        User user = new User(name, name);

        assertNull(user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void createCompleteUserTest() {
        Long id = 1L;
        String name = "test";
        String password = "test";

        User user = new User(id, name, name);

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
