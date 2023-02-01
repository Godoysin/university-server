package com.luxoft.producer.service;

import com.luxoft.producer.interfaces.UserServiceInterface;
import com.luxoft.producer.model.User;
import com.luxoft.producer.repository.UserRepository;
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

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

}
