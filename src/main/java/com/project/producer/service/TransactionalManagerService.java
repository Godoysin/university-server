package com.project.producer.service;

import com.project.producer.db.model.Role;
import com.project.producer.db.model.User;
import com.project.producer.service.validation.RoleValidationService;
import com.project.producer.service.validation.UserValidationService;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class TransactionalManagerService {

    private final RoleValidationService roleValidationService;

    private final UserManagementService userManagementService;

    private final UserValidationService userValidationService;

    @Autowired
    public TransactionalManagerService(RoleValidationService roleValidationService, UserManagementService userManagementService, UserValidationService userValidationService, ValidationService validationService) {
        this.roleValidationService = roleValidationService;
        this.userManagementService = userManagementService;
        this.userValidationService = userValidationService;
    }

    public void createUserWithRole(User user, Set<Role> roleSet) {
        try {
            userValidationService.validateUserInsertion(user);
            roleValidationService.validateRoleInsertion(roleSet);

            userManagementService.createUserWithRole(user, roleSet);
        } catch (Exception e) {
            log.error("Exception thrown: {}", e.toString());
            throw new BadRequestException();
        }
    }

}
