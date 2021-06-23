package com.sujin.spring.web.config.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sujin.spring.core.BaseMapper;
import com.sujin.spring.core.CrudController;
import com.sujin.spring.core.Service;
import com.sujin.spring.domain.users.User;

@Controller
@RequestMapping(UserController.BASE_URL)
public class UserController extends CrudController<User, UserDto, Long> {

    static  final String BASE_URL = "/users";
    private static final String TEMPLATE_DIR = "pages/users/";

    protected UserController(
        @Autowired Service<User, Long> service,
        @Autowired BaseMapper<User, UserDto> mapper) {
        super(TEMPLATE_DIR, service, mapper);
    }

    @Override
    protected String getDefaultUrl() {
        return BASE_URL;
    }
}
