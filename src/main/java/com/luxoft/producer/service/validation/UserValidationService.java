package com.luxoft.producer.service.validation;

import com.luxoft.producer.db.model.User;
import com.luxoft.producer.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserValidationService {

    private final ValidationService validationService;

    @Autowired
    UserValidationService (ValidationService validationService) {
        this.validationService = validationService;
    }

    public void validateUserInsertion(User user) {
        Set<ConstraintViolation<User>> constraintViolationSet = validationService.getValidator().validate(user);

        if (user.getId() != null)
            throw new ValidationException("Id must be null on insertion");

        if (!constraintViolationSet.isEmpty()) {
            Optional<ConstraintViolation<User>> constraintViolationOptional = constraintViolationSet.stream().findFirst();

            if (constraintViolationOptional.isPresent()) {
                throw new ValidationException(constraintViolationOptional.get().getPropertyPath().toString() + " " + constraintViolationOptional.get().getMessage());
            }
        }
    }

}
