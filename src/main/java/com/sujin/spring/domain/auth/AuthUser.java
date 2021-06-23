package com.sujin.spring.domain.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sujin.spring.core.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "auth_users")
@EqualsAndHashCode(callSuper = true)
@ToString
public class AuthUser extends BaseEntity<Long> implements UserDetails, Serializable {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Column(nullable = false, unique = true)
    @Setter
    private String username;

    @Setter
    private String password;

    @Column(name = "account_non_locked")
    @Setter
    private boolean accountNonLocked = true;

    @Column(name = "account_non_expired")
    @Setter
    private boolean accountNonExpired = true;

    @Column(name = "credentials_non_expired")
    @Setter
    private boolean credentialsNonExpired = true;

    @Setter
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
