package com.luxoft.producer.service;

import com.luxoft.producer.db.model.Role;
import com.luxoft.producer.db.model.User;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class TransactionalManagerService {

    private final UserManagementService userManagementService;

    private final ValidationService validationService;

    @Autowired
    public TransactionalManagerService(UserManagementService userManagementService, ValidationService validationService) {
        this.userManagementService = userManagementService;
        this.validationService = validationService;
    }

    public void createUserWithRole(User user, Set<Role> roleSet) {
        try {
            user.validateInsert(validationService.getValidator());
            roleSet.stream().forEach(e -> e.validateInsert(validationService.getValidator()));

            userManagementService.createUserWithRole(user, roleSet);
        } catch (Exception e) {
            log.error("Exception thrown: {}", e.toString());
            throw new BadRequestException();
        }
    }

}
