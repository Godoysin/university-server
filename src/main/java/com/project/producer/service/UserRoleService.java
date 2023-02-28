package com.project.producer.service;

import com.project.producer.db.interfaces.UserRoleServiceInterface;
import com.project.producer.db.model.Role;
import com.project.producer.db.model.User;
import com.project.producer.db.model.UserRole;
import com.project.producer.db.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserRoleService implements UserRoleServiceInterface {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void assignNewRolesToUser(User user, Set<Role> roleSet) {
        List<UserRole> userRoleList = generate(user, roleSet);

        userRoleRepository.saveAll(userRoleList);
    }

    @Override
    public void deleteRolesFromUser(User user, Set<Role> roleSet) {
        List<UserRole> userRoleList = generate(user, roleSet);

        userRoleRepository.deleteAll(userRoleList);
    }

    protected List<UserRole> generate(User user, Set<Role> roleSet) {
        List<UserRole> userRoleList = new ArrayList<>();

        for (Role role : roleSet)
            userRoleList.add(new UserRole(user.getId(), role.getName()));

        return userRoleList;
    }
}
