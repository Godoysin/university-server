package com.project.producer.db.interfaces;

import com.project.producer.db.model.Role;
import com.project.producer.db.model.User;

import java.util.Set;

public interface UserRoleServiceInterface {

    void assignNewRolesToUser(User user, Set<Role> role);
    void deleteRolesFromUser(User user, Set<Role> role);
}
