package com.sujin.spring.domain.auth;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.sujin.spring.core.BaseRepository;
import com.sujin.spring.core.BaseService;
import com.sujin.spring.core.BaseSpecBuilder;


@Service
public class AuthUserService extends BaseService<AuthUser, Long> {

    protected AuthUserService(
        BaseRepository<AuthUser, Long> repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<AuthUser> getSpec(Map<String, String> filterParams) {
        return null;
    }
}
