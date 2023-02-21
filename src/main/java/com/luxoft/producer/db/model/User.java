package com.luxoft.producer.db.model;

import com.luxoft.producer.security.constants.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

    public User() {
    }

    public User(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public User(Long id, String name, String password) {
        setId(id);
        setName(name);
        setPassword(password);
    }

    public Set<String> getAuthorities() {
        return Set.of(RoleEnum.ROLE.getRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public void validateInsert(Validator validator) {
        Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(this);

        if (getId() != null)
            throw new ValidationException("Id must be null on insertion");

        if (!constraintViolationSet.isEmpty()) {
            Optional<ConstraintViolation<User>> constraintViolationOptional = constraintViolationSet.stream().findFirst();

            if (constraintViolationOptional.isPresent()) {
                throw new ValidationException(constraintViolationOptional.get().getPropertyPath().toString() + " " + constraintViolationOptional.get().getMessage());
            }
        }

    }

}
