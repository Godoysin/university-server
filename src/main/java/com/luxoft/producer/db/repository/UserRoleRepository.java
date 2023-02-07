package com.luxoft.producer.db.repository;

import com.luxoft.producer.db.model.UserRole;
import com.luxoft.producer.db.model.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {

}
