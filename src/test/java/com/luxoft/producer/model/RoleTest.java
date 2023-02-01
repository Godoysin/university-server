package com.luxoft.producer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RoleTest {

    @Test
    public void createRoleTest() {
        String roleName = "role";

        Role role = new Role(roleName);

        assertEquals(roleName, role.getName());
    }

    @Test
    public void createEmptyRoleTest() {
        Role role = new Role();

        assertNull(role.getName());
    }

}
