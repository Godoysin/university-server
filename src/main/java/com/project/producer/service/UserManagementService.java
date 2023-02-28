package com.project.producer.service;

import com.project.producer.db.model.Role;
import com.project.producer.db.model.User;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Slf4j
public class UserManagementService {

    private final UserRoleService userRoleService;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserManagementService(UserService userService, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUserWithRole(User user, Set<Role> roleSet) {
        if (user.getId() != null)
            throw new BadRequestException();

        // Stores hashed password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.createUser(user);
        userRoleService.assignNewRolesToUser(user, roleSet);
    }

}
