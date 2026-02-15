package com.example.foodordering.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    //down below we only check the type of the collection since in this case we do not have any specific type so we use
    //placeholder called ? that means we do not know the type of collection so it can be anything and it extends to GrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Step 2: Convert your Role to a GrantedAuthority.
        // Spring Security expects roles to start with "ROLE_" (e.g., ROLE_USER)
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
        //here we are using a ready made class present in the spring security which turns your role into a format Spring
        //Security understands so it can check if you’re allowed to do something.
        //in short  SimpleGrantedAuthority makes the role such that spring security can understand
        //and after that List.of takes that and save it in list because one person can have multiple roles
    }

    @Override
    public String getUsername() { //getUsername is a predefined method in UserDetail interface it doesn't care what you call it by name
        // here we use username and email as same so that user doesn't return
        // Since you use email for login, return email here
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Set to true so the account doesn't "expire"
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Set to true so the account isn't "locked"
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Set to true so password doesn't "expire"
    }

    @Override
    public boolean isEnabled() {
        return true; // Set to true so the user is "active"
    }

    // --- Standard Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }
}
//Where are these methods used in your code?
//
//1. In User.java - getAuthorities():
//return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
//← Uses .name() to get "USER" or "ADMIN"
//
//2. In AuthController - login():
//jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//← Uses .name() to put role in JWT token
//
//3. Hibernate uses valueOf() internally:
//When reading from database, Hibernate calls Role.valueOf("USER") to convert the string back to the enum!
