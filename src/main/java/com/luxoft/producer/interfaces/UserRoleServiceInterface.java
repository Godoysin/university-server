package com.luxoft.producer.interfaces;

import com.luxoft.producer.model.Role;
import com.luxoft.producer.model.User;

import java.util.Set;

public interface UserRoleServiceInterface {

    void assignNewRolesToUser(User user, Set<Role> role);
    void deleteRolesFromUser(User user, Set<Role> role);
}
