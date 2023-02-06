package com.luxoft.producer.service;

import com.luxoft.producer.model.Role;
import com.luxoft.producer.model.User;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Slf4j
public class UserManagementService {

    private final UserRoleService userRoleService;
    private final UserService userService;

    @Autowired
    public UserManagementService(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Transactional
    public void createUserWithRole(User user, Set<Role> roleSet) {
        if (user.getId() != null)
            throw new BadRequestException();

        userService.createUser(user);
        userRoleService.assignNewRolesToUser(user, roleSet);
    }

}
