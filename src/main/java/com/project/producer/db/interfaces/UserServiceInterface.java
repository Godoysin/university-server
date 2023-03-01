package com.project.producer.db.interfaces;

import com.project.producer.db.model.User;

import java.util.Optional;

public interface UserServiceInterface {

    void createUser(User user);
    Optional<User> readUser(Long id);
    void deleteUser(User user);
}
