package com.project.producer.service.validation;

import com.project.producer.db.model.User;
import com.project.producer.service.ValidationService;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserValidationServiceTest {

    @InjectMocks
    private UserValidationService userValidationService;

    @Mock
    private ValidationService validationServiceMock;

    private static Validator validator;

    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
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
        when(validationServiceMock.getValidator()).thenReturn(validator);

        // when
        Executable executable = () -> userValidationService.validateUserInsertion(user);

        // then
        assertThrows(ValidationException.class, executable);
    }

    @Test
    void shouldValidateUser() {
        // given
        User user = new User("user", "password");
        when(validationServiceMock.getValidator()).thenReturn(validator);

        // when
        Executable executable = () -> userValidationService.validateUserInsertion(user);

        // then
        assertDoesNotThrow(executable);
    }

}
