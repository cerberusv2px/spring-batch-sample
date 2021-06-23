package com.sujin.spring.domain.auth;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sujin.spring.core.BaseRepository;


@Repository
public interface AuthUserRepository extends BaseRepository<AuthUser, Long> {

    AuthUser findByUsername(String username);

    AuthUser findByEmail(String email);

    @Query(
        value = "SELECT * FROM users WHERE id IS NOT NULL LIMIT 1",
        nativeQuery = true
    )
    AuthUser checkUser();
}
