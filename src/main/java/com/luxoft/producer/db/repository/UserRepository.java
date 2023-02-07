package com.luxoft.producer.db.repository;

import com.luxoft.producer.db.model.User;
import com.luxoft.producer.db.model.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByName(String name);

    @Query(value = "SELECT * FROM user INNER JOIN user_role ON user.id = user_role.user_id WHERE user.name = ?1", nativeQuery = true)
    Optional<UserWithRoles> findUserAndRolesByName(String name);

}
