package com.luxoft.producer.interfaces;

import com.luxoft.producer.model.Role;

import java.util.Optional;

public interface RoleServiceInterface {

    public void createRole(Role role);
    public Optional<Role> readRole(String id);
    public void deleteRole(Role role);
}
