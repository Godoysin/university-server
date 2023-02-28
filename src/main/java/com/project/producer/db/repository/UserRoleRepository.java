package com.project.producer.db.repository;

import com.project.producer.db.model.UserRole;
import com.project.producer.db.model.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {

}
