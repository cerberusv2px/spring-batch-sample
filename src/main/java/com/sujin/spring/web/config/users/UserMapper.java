package com.sujin.spring.web.config.users;

import org.mapstruct.Mapper;

import com.sujin.spring.core.BaseMapper;
import com.sujin.spring.domain.users.User;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class UserMapper extends BaseMapper<User, UserDto> {

}
