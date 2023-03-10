package com.project.producer.controller.model;

import com.project.producer.db.model.Role;
import com.project.producer.db.model.User;

import java.util.Set;

public class UserAndRoles {

    private User user;

    private Set<Role> roleSet;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}
