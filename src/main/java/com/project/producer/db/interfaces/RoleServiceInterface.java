package com.project.producer.db.interfaces;

import com.project.producer.db.model.Role;

import java.util.Optional;

public interface RoleServiceInterface {

    void createRole(Role role);
    Optional<Role> readRole(String id);
    void deleteRole(Role role);
}
