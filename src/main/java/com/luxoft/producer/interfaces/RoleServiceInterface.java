package com.luxoft.producer.interfaces;

import com.luxoft.producer.model.Role;

import java.util.Optional;

public interface RoleServiceInterface {

    void createRole(Role role);
    Optional<Role> readRole(String id);
    void deleteRole(Role role);
}
