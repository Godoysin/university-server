package com.luxoft.producer.db.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride( name = "userId", column = @Column(name = "user_id")),
            @AttributeOverride( name = "roleName", column = @Column(name = "role_name")),
    })
    private UserRolePK userRolePK;

    public UserRole() {}

    public UserRole(Long userId, String roleName) {
        setUserRolePK(new UserRolePK(userId, roleName));
    }

    public UserRolePK getUserRolePK() {
        return userRolePK;
    }

    public void setUserRolePK(UserRolePK userRolePK) {
        this.userRolePK = userRolePK;
    }

}
