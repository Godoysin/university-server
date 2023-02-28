package com.project.producer.db.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Embeddable
public class UserRolePK implements Serializable {

    @NotNull
    private Long userId;

    @NotBlank
    private String roleName;

    public UserRolePK() {}

    public UserRolePK(Long userId, String roleName) {
        setUserId(userId);
        setRoleName(roleName);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
