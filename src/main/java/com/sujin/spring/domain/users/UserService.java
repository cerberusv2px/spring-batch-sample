package com.sujin.spring.domain.users;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.sujin.spring.core.BaseRepository;
import com.sujin.spring.core.BaseService;
import com.sujin.spring.core.BaseSpecBuilder;

@Service
public class UserService extends BaseService<User, Long> {

    protected UserService(
        BaseRepository<User, Long> repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<User> getSpec(Map<String, String> filterParams) {
        return null;
    }
}
