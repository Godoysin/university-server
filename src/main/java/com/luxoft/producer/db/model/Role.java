package com.luxoft.producer.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

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
}
