package com.luxoft.producer.service.validation;

import com.luxoft.producer.db.model.Role;
import com.luxoft.producer.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleValidationService {

    private final ValidationService validationService;

    @Autowired
    RoleValidationService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public void validateRoleInsertion(Set<Role> roleSet) {
        roleSet.stream().forEach(this::validateRoleInsertion);
    }

    public void validateRoleInsertion(Role role) {
        Set<ConstraintViolation<Role>> constraintViolationSet = validationService.getValidator().validate(role);

        if (!constraintViolationSet.isEmpty()) {
            Optional<ConstraintViolation<Role>> constraintViolationOptional = constraintViolationSet.stream().findFirst();

            if (constraintViolationOptional.isPresent()) {
                throw new ValidationException(constraintViolationOptional.get().getPropertyPath().toString() + " " + constraintViolationOptional.get().getMessage());
            }
        }
    }

}
