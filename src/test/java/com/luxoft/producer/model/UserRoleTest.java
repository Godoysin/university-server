package com.luxoft.producer.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserRoleTest {

    private static Validator validator;

    @BeforeAll
    public static void init() {
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    public void createUserRoleTest() {
        Long userId = 1L;
        String roleName = "userName";

        UserRole userRole = new UserRole(userId, roleName);

        assertEquals(userId, userRole.getUserRolePK().getUserId());
        assertEquals(roleName, userRole.getUserRolePK().getRoleName());

        Set<ConstraintViolation<UserRole>> constraintViolationSet = validator.validate(userRole);
        assertTrue(constraintViolationSet.isEmpty());
    }

    @Test
    public void createEmptyUserRoleTest() {
        UserRole userRole = new UserRole();

        assertNull(userRole.getUserRolePK());

        Set<ConstraintViolation<UserRole>> constraintViolationSet = validator.validate(userRole);
        assertTrue(constraintViolationSet.isEmpty());
    }

    @Test
    public void createEmptyUserRoleWithPKTest() {
        UserRole userRole = new UserRole(null, null);

        assertNotNull(userRole.getUserRolePK());

        Set<ConstraintViolation<UserRolePK>> constraintViolationSet = validator.validate(userRole.getUserRolePK());
        assertFalse(constraintViolationSet.isEmpty());
        assertEquals(2, constraintViolationSet.size());
    }

}
