package com.luxoft.producer.service;

import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

    @InjectMocks
    private ValidationService validationService;

    @Test
    void shouldVerifyValidatorCreationAndReturning() {
        // given

        // when
        Validator validatorReturned = validationService.getValidator();

        // then
        assertNotNull(validatorReturned);
    }

}
