package com.luxoft.producer.repository;

import com.luxoft.producer.model.UserRole;
import com.luxoft.producer.model.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {

}
