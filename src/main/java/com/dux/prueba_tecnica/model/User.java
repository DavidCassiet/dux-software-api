package com.dux.prueba_tecnica.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    private String password;

    private Boolean status;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getStatus() {
        return status;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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

    public static class Builder {
        private final String username;
        private final String password;
        private Boolean status;
        private final Role role;

        public Builder(String username, String password, Role role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public Builder status(Boolean status) {
            this.status = status;
            return this;
        }

        public User build() {
            User user = new User();
            user.username = this.username;
            user.password = this.password;
            user.status = this.status;
            user.role = this.role;
            return user;
        }
    }
}
