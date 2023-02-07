package com.luxoft.producer.db.interfaces;

import com.luxoft.producer.db.model.User;

import java.util.Optional;

public interface UserServiceInterface {

    void createUser(User user);
    Optional<User> readUser(Long id);
    void deleteUser(User user);
}
