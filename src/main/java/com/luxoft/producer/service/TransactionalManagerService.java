package com.luxoft.producer.service;

import com.luxoft.producer.model.Role;
import com.luxoft.producer.model.User;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class TransactionalManagerService {

    private final UserManagementService userManagementService;

    @Autowired
    public TransactionalManagerService(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    public void createUserWithRole(User user, Set<Role> roleSet) {
        try {
            userManagementService.createUserWithRole(user, roleSet);
        } catch (Exception e) {
            log.error("Test: {}", e.toString());
            throw new BadRequestException();
        }
    }

}
