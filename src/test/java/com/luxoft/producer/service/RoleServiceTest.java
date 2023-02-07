package com.luxoft.producer.service;

import com.luxoft.producer.db.model.Role;
import com.luxoft.producer.db.repository.RoleRepository;
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
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepositoryMock;

    @InjectMocks
    private RoleService roleServiceMock;

    private Role role;

    @BeforeEach
    public void createRole() {
        String name = "role";

        role = new Role(name);
    }

    @Test
    public void createRoleTest() {
        roleServiceMock.createRole(role);
        verify(roleRepositoryMock).save(role);
    }

    @Test
    public void readFoundRoleTest() {
        when(roleRepositoryMock.findById(role.getName())).thenReturn(Optional.of(role));

        Optional<Role> optionalRole = roleServiceMock.readRole(role.getName());

        verify(roleRepositoryMock).findById(role.getName());
        assertTrue(optionalRole.isPresent());
        assertEquals(role.getName(), optionalRole.get().getName());
    }

    @Test
    public void readNotFoundRoleTest() {
        when(roleRepositoryMock.findById(role.getName())).thenReturn(Optional.empty());

        Optional<Role> optionalUser = roleServiceMock.readRole(role.getName());

        verify(roleRepositoryMock).findById(role.getName());
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    public void deleteRoleTest() {
        roleServiceMock.deleteRole(role);

        verify(roleRepositoryMock).delete(role);
    }
}
