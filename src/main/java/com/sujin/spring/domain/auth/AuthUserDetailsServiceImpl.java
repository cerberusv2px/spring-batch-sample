package com.sujin.spring.domain.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsServiceImpl implements UserDetailsService {

    private final AuthUserRepository repository;

    public AuthUserDetailsServiceImpl(
        @Autowired AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser authUser = repository.findByUsername(s);

        if (null == authUser) {
            throw new UsernameNotFoundException("Invalid username " + s);
        }

        return authUser;
    }
}
