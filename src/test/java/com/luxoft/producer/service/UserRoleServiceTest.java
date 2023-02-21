package com.luxoft.producer.service;

import com.luxoft.producer.db.model.Role;
import com.luxoft.producer.db.model.User;
import com.luxoft.producer.db.model.UserRole;
import com.luxoft.producer.db.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleServiceMock;

    private List<UserRole> userRoleList;
    private User user;
    private Set<Role> roleSet;

    @BeforeEach
    public void init() {
        user = new User();
        roleSet = new HashSet<>();
        userRoleList = userRoleServiceMock.generate(user, roleSet);
    }

    @Test
    void assignNewRolesToUserTest() {
        userRoleServiceMock.assignNewRolesToUser(user, roleSet);
        verify(userRoleRepository).saveAll(userRoleList);
    }

    @Test
    void deleteRolesFromUserTest() {
        userRoleServiceMock.deleteRolesFromUser(user, roleSet);
        verify(userRoleRepository).deleteAll(userRoleList);
    }

}
