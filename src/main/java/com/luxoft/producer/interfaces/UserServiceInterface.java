package com.luxoft.producer.interfaces;

import com.luxoft.producer.model.User;

import java.util.Optional;

public interface UserServiceInterface {

    void createUser(User user);
    Optional<User> readUser(Long id);
    void deleteUser(User user);
}
