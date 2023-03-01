package com.project.producer.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @NotBlank
    private String name;

    public Role() {}

    public Role(String roleName) {
        setName(roleName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void validateInsert(Validator validator) {
        Set<ConstraintViolation<Role>> constraintViolationSet = validator.validate(this);

        if (!constraintViolationSet.isEmpty()) {
            Optional<ConstraintViolation<Role>> constraintViolationOptional = constraintViolationSet.stream().findFirst();

            if (constraintViolationOptional.isPresent()) {
                throw new ValidationException(constraintViolationOptional.get().getPropertyPath().toString() + " " + constraintViolationOptional.get().getMessage());
            }
        }
    }
}
