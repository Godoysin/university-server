package com.project.producer.service;

import com.project.producer.db.model.Role;
import com.project.producer.db.repository.RoleRepository;
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
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepositoryMock;

    @InjectMocks
    private RoleService roleServiceMock;

    private Role role;

    @BeforeEach
    void createRole() {
        String name = "role";

        role = new Role(name);
    }

    @Test
    void shouldVerifyCreateARole() {
        // given
        // when
        roleServiceMock.createRole(role);

        // then
        verify(roleRepositoryMock).save(role);
    }

    @Test
    void shouldVerifyGettingARole() {
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
    void shouldVerifyGettingAnEmptyRoleOptional() {
        // given
        when(roleRepositoryMock.findById(role.getName())).thenReturn(Optional.empty());

        // when
        Optional<Role> optionalUser = roleServiceMock.readRole(role.getName());

        // then
        verify(roleRepositoryMock).findById(role.getName());
        assertTrue(optionalUser.isEmpty());
    }

    @Test
    void shouldPassThroughDeleteInRoleRepository() {
        // given
        // when
        roleServiceMock.deleteRole(role);

        // then
        verify(roleRepositoryMock).delete(role);
    }
}
