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

    protected static Stream<Arguments> testArguments() {
        return Stream.of(
                Arguments.of(new User()),
                Arguments.of(new User("", "")),
                Arguments.of(new User("notBlank", "")),
                Arguments.of(new User("", "notBlack"))
        );
    }

    @ParameterizedTest
    @MethodSource("testArguments")
    void shouldThrowValidationException(User user) {
        // given

        // when
        Executable executable = () -> user.validateInsert(validationService.getValidator());

        // then
        assertThrows(ValidationException.class, executable);
    }

    @Test
    void shouldValidateUser() {
        // given
        User user = new User("user", "password");

        // when
        Executable executable = () -> user.validateInsert(validationService.getValidator());

        // then
        assertDoesNotThrow(executable);
    }

}
