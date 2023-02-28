package com.project.producer.db.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserWithRolesTest {

    @Test
    void setOfRolesAsStringTest() {
        Long id = 1L;
        Set<String> stringSet = Set.of("role1", "role2");
        Set<UserRole> userRoleSet = new HashSet<>();

        for (String role : stringSet) {
            UserRole userRole = new UserRole(id, role);
            userRoleSet.add(userRole);
        }

        UserWithRoles userWithRoles = new UserWithRoles();
        userWithRoles.setRoles(userRoleSet);

        Set<String> roleSet = userWithRoles.getUserRolesAsString();

        for (String role : roleSet)
            assertTrue(stringSet.contains(role));
    }

}
