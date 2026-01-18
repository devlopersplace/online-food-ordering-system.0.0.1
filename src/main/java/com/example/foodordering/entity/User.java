package com.example.foodordering.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails { // Step 1: Implement UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // --- Spring Security Methods ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Step 2: Convert your Role to a GrantedAuthority.
        // Spring Security expects roles to start with "ROLE_" (e.g., ROLE_USER)
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
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

//package com.example.foodordering.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "users")
//public class User  {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@Column(unique = true, nullable = false)
//	private String email;
//	
//	@Column(nullable = false)
//	private String password;
//	
//	@Enumerated(EnumType.STRING) //Enumerated keyword makes your list stored in the db from int to string 
//	private Role role;
//	
//	//additional
//	public Long getId() {
//	    return id;
//	}
//
//	public void setId(Long id) {
//	    this.id = id;
//	}
//
//	public String getEmail() {
//	    return email;
//	}
//
//	public void setEmail(String email) {
//	    this.email = email;
//	}
//
//	public String getPassword() {
//	    return password;
//	}
//
//	public void setPassword(String password) {
//	    this.password = password;
//	}
//
//	public Role getRole() {
//	    return role;
//	}
//
//	public void setRole(Role role) {
//	    this.role = role;
//	}
//
//}
