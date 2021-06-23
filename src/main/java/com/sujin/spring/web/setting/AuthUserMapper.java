package com.sujin.spring.web.setting;

import org.mapstruct.Mapper;

import com.sujin.spring.core.BaseMapper;
import com.sujin.spring.domain.auth.AuthUser;

@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class AuthUserMapper extends BaseMapper<AuthUser, AuthUserDto> {

}
