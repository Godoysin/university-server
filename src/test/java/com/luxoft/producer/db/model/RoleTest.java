package com.luxoft.producer.db.model;

import com.luxoft.producer.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleTest {

    @Spy
    ValidationService validationService;

    @Test
    void createRoleTest() {
        String roleName = "role";

        Role role = new Role(roleName);

        assertEquals(roleName, role.getName());
    }

    @Test
    void createEmptyRoleTest() {
        Role role = new Role();

        assertNull(role.getName());
    }

}
