package com.luxoft.producer.model;

import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class UserWithRoles extends User {

    @OneToMany
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private Set<UserRole> userRoles;

    public UserWithRoles() {
        super();
    }

    public UserWithRoles(String name, String password) {
        super(name, password);
    }

    public UserWithRoles(Long id, String name, String password) {
        super(id, name, password);
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public Set<String> getUserRolesAsString() {

        return userRoles.stream().map(e -> e.getUserRolePK().getRoleName()).collect(Collectors.toSet());
    }

    public void setRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}
