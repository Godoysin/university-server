package com.luxoft.producer.service;

import com.luxoft.producer.db.interfaces.UserServiceInterface;
import com.luxoft.producer.db.model.User;
import com.luxoft.producer.db.model.UserWithRoles;
import com.luxoft.producer.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> readUser(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<UserWithRoles> getUserAndRoles(String name) {
        return userRepository.findUserAndRolesByName(name);
    }

}
