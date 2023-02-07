package com.luxoft.producer.service;

import com.luxoft.producer.db.interfaces.RoleServiceInterface;
import com.luxoft.producer.db.model.Role;
import com.luxoft.producer.db.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void createRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Optional<Role> readRole(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

}
