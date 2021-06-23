package com.sujin.spring.domain.auth.registration;


import com.sujin.spring.core.exception.PersistenceConstraintViolationException;
import com.sujin.spring.domain.auth.AuthUser;

public interface UserRegistrationService {

    boolean userExist();

    AuthUser registerUser(AuthUser u) throws PersistenceConstraintViolationException;
}
