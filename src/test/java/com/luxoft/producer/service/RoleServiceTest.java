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
    public void shouldVerifyCreateARole() {
        // given
        // when
        roleServiceMock.createRole(role);

        // then
        verify(roleRepositoryMock).save(role);
    }

    @Test
    public void shouldVerifyGettingARole() {
        // given
        when(roleRepositoryMock.findById(role.getName())).thenReturn(Optional.of(role));

        // when
        Optional<Role> optionalRole = roleServiceMock.readRole(role.getName());

        // then
        verify(roleRepositoryMock).findById(role.getName());
        assertTrue(optionalRole.isPresent());
        assertEquals(role.getName(), optionalRole.get().getName());
    }

    @Test
    public void shouldVerifyGettingAnEmptyRoleOptional() {
        // given
        when(roleRepositoryMock.findById(role.getName())).thenReturn(Optional.empty());

        // when
        Optional<Role> optionalUser = roleServiceMock.readRole(role.getName());

        // then
        verify(roleRepositoryMock).findById(role.getName());
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    public void shouldPassThroughDeleteInRoleRepository() {
        // given
        // when
        roleServiceMock.deleteRole(role);

        // then
        verify(roleRepositoryMock).delete(role);
    }
}
