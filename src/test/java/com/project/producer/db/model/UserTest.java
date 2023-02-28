package com.project.producer.db.model;

import com.project.producer.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Spy
    ValidationService validationService;

    @Test
    void createUserTest() {
        String name = "testName";
        String password = "testPassword";

        User user = new User(name, password);

        assertNull(user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    void createCompleteUserTest() {
        Long id = 1L;
        String name = "testName";
        String password = "testPassword";

        User user = new User(id, name, password);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    void createEmptyUserTest() {
        User user = new User();

        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getPassword());
    }

}
