package com.sujin.spring.web.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.sujin.spring.core.BaseMapper;
import com.sujin.spring.core.exception.PersistenceConstraintViolationException;
import com.sujin.spring.domain.auth.AuthUser;
import com.sujin.spring.domain.auth.registration.UserRegistrationService;
import com.sujin.spring.web.setting.AuthUserDto;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/register")
@Slf4j
public class RegistrationController {

    private static final String TEMPLATE = "pages/security/auth/registration/register";

    private static final String FORBIDDEN_MESSAGE = "Account Registration is enabled only once during application setup";

    private final BaseMapper<AuthUser, AuthUserDto> mapper;
    private final UserRegistrationService registrationService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(
        BaseMapper<AuthUser, AuthUserDto> mapper,
        UserRegistrationService registrationService,
        PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String register(@ModelAttribute("dto") AuthUserDto dto) {
        if (!registrationService.userExist()) {
            return TEMPLATE;
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, FORBIDDEN_MESSAGE);
    }

    @PostMapping("")
    public String register(@ModelAttribute("dto") @Valid AuthUserDto dto, Model model,
        BindingResult validationResult, RedirectAttributes redirect) {

        if (registrationService.userExist()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, FORBIDDEN_MESSAGE);
        }

        if (validationResult.hasErrors()) {
            model.addAttribute("error", "Validation Failed");
            return TEMPLATE;
        }

        final AuthUser entity = mapper.mapDtoToEntity(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        try {
            registrationService.registerUser(entity);
            redirect.addFlashAttribute("success", "Account registered successfully");

            return "redirect:/login";
        } catch (PersistenceConstraintViolationException e) {
            log.error("Error while saving record {}", entity, e);
            model.addAttribute("error",
                "Error occurred while saving records (" + e.getLocalizedMessage() + ")");
            return TEMPLATE;
        }

    }

}
