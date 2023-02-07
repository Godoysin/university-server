package com.luxoft.producer.db.interfaces;

import com.luxoft.producer.db.model.Role;
import com.luxoft.producer.db.model.User;

import java.util.Set;

public interface UserRoleServiceInterface {

    void assignNewRolesToUser(User user, Set<Role> role);
    void deleteRolesFromUser(User user, Set<Role> role);
}
