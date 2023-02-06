package com.luxoft.producer.service;

import com.luxoft.producer.model.User;
import com.luxoft.producer.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userServiceMock;

    private User user;

    @BeforeEach
    public void init() {
        Long id = 1L;
        String name = "name";
        String password = "password";

        user = new User(name, password);
    }

    @Test
    public void createUserTest() {
        userServiceMock.createUser(user);
        verify(userRepositoryMock).save(user);
    }

    @Test
    public void readFoundUserTest() {
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.of(user));

        Optional<User> optionalUser = userServiceMock.readUser(user.getId());

        verify(userRepositoryMock).findById(user.getId());
        assertTrue(optionalUser.isPresent());
        assertEquals(user.getId(), optionalUser.get().getId());
        assertEquals(user.getName(), optionalUser.get().getName());
        assertEquals(user.getPassword(), optionalUser.get().getPassword());
    }

    @Test
    public void readNotFoundUserTest() {
        when(userRepositoryMock.findById(user.getId())).thenReturn(Optional.empty());

        Optional<User> optionalUser = userServiceMock.readUser(user.getId());

        verify(userRepositoryMock).findById(user.getId());
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    public void deleteRoleTest() {
        userServiceMock.deleteUser(user);

        verify(userRepositoryMock).delete(user);
    }
}
