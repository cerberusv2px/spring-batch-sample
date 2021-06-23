package com.sujin.spring.domain.users;

import org.springframework.stereotype.Repository;

import com.sujin.spring.core.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}
