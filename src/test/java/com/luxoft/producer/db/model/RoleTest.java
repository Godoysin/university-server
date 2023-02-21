package com.luxoft.producer.db.model;

import com.luxoft.producer.service.ValidationService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

    protected static Stream<Arguments> testArguments() {
        return Stream.of(
                Arguments.of(new Role()),
                Arguments.of(new Role(""))
        );
    }

    @ParameterizedTest
    @MethodSource("testArguments")
    void shouldThrowValidationException(Role role) {
        // given

        // when
        Executable executable = () -> role.validateInsert(validationService.getValidator());

        // then
        assertThrows(ValidationException.class, executable);
    }

    @Test
    void shouldValidateRole() {
        // given
        Role role = new Role("role");

        // when
        Executable executable = () -> role.validateInsert(validationService.getValidator());

        // then
        assertDoesNotThrow(executable);
    }

}
