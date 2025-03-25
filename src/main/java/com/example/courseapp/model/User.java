package com.example.courseapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
@Data
public class User implements UserDetails {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private boolean isInstructor = false;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = isInstructor ? "INSTRUCTOR" : "STUDENT";
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
}