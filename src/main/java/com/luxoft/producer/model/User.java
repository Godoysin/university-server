package com.luxoft.producer.model;

import com.luxoft.producer.security.constants.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
//public class User implements UserDetails {
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;

//    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//    private boolean enabled;

    public User() {
//        super();
    }

    public User(String name, String password) {
//        super();
        setName(name);
        setPassword(password);
//        setAccountNonExpired(true);
//        setAccountNonLocked(true);
//        setCredentialsNonExpired(true);
//        setEnabled(true);
    }

    public User(Long id, String name, String password) {
        setId(id);
        setName(name);
        setPassword(password);
//        setAccountNonExpired(true);
//        setAccountNonLocked(true);
//        setCredentialsNonExpired(true);
//        setEnabled(true);
    }

//    @Override
    public Set<String> getAuthorities() {
        return Set.of(RoleEnum.ROLE.getRole());
//        return List.of(new SimpleGrantedAuthority(getName()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

//    public void setAccountNonExpired(boolean accountNonExpired) {
//        this.accountNonExpired = accountNonExpired;
//    }

//    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

//    public void setAccountNonLocked(boolean accountNonLocked) {
//        this.accountNonLocked = accountNonLocked;
//    }

//    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

//    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//        this.credentialsNonExpired = credentialsNonExpired;
//    }

//    @Override
    public boolean isEnabled() {
        return false;
    }

//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
}
