package com.luxoft.producer.controller;

import com.luxoft.producer.controller.model.UserAndRoles;
import com.luxoft.producer.service.TransactionalManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("university/users")
@Slf4j
public class UserController {

    private final TransactionalManagerService transactionalManagerService;

    @Autowired
    public UserController(TransactionalManagerService transactionalManagerService) {
        this.transactionalManagerService = transactionalManagerService;
    }

    @PostMapping("/new-user")
    public void createUser(@RequestBody UserAndRoles userAndRoles) {
        transactionalManagerService.createUserWithRole(userAndRoles.getUser(), userAndRoles.getRoleSet());
    }

}
