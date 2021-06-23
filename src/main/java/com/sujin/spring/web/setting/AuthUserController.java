package com.sujin.spring.web.setting;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sujin.spring.core.BaseMapper;
import com.sujin.spring.core.CrudController;
import com.sujin.spring.core.Service;
import com.sujin.spring.domain.auth.AuthUser;

@Controller
@RequestMapping(AuthUserController.DEFAULT_URL)
public class AuthUserController extends CrudController<AuthUser, AuthUserDto, Long> {

    static final String DEFAULT_URL = "/settings/users";

    private final PasswordEncoder passwordEncoder;

    protected AuthUserController(
        Service<AuthUser, Long> service,
        BaseMapper<AuthUser, AuthUserDto> mapper,
        PasswordEncoder passwordEncoder) {
        super("pages/setting/users/", service, mapper);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected String getDefaultUrl() {
        return DEFAULT_URL;
    }

    @Override
    protected boolean canDelete() {
        return false;
    }

    @Override
    protected void beforeSave(AuthUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
