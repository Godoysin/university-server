package com.luxoft.producer.service;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private final Validator validator;

    public ValidationService() {
        try(ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public Validator getValidator() {
        return validator;
    }

}
