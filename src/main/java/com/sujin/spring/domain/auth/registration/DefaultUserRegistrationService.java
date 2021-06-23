package com.sujin.spring.domain.auth.registration;

import org.springframework.stereotype.Service;

import com.sujin.spring.core.BaseService;
import com.sujin.spring.core.exception.PersistenceConstraintViolationException;
import com.sujin.spring.domain.auth.AuthUser;
import com.sujin.spring.domain.auth.AuthUserRepository;
import com.sujin.spring.domain.users.UserRepository;

@Service
public class DefaultUserRegistrationService implements UserRegistrationService {

    private final BaseService<AuthUser, Long> userService;
    private final AuthUserRepository userRepository;

    private boolean userExists = false;

    public DefaultUserRegistrationService(
        BaseService<AuthUser, Long> userService,
        AuthUserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean userExist() {
        if (!userExists) {
            final AuthUser u = userRepository.checkUser();
            if (null != u) {
                userExists = true;
            }
        }

        return userExists;
    }

    @Override
    public AuthUser registerUser(AuthUser u) throws PersistenceConstraintViolationException {
        return userService.save(u);
    }
}
