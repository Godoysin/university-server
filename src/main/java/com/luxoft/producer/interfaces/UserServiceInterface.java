package com.luxoft.producer.interfaces;

import com.luxoft.producer.model.User;

import java.util.Optional;

public interface UserServiceInterface {

    public void createUser(User user);
    public Optional<User> readUser(Long id);
    public void deleteUser(User user);
}
