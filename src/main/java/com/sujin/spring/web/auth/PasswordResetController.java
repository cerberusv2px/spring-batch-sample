package com.sujin.spring.web.auth;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sujin.spring.domain.auth.AuthUser;
import com.sujin.spring.domain.auth.resetpassword.PasswordResetService;
import com.sujin.spring.domain.auth.resetpassword.PasswordResetToken;
import com.sujin.spring.service.email.EmailRequest;
import com.sujin.spring.service.email.EmailService;
import com.sujin.spring.service.email.EmailServiceException;


//@Controller
/*public class PasswordResetController {

    private static final String FORGET_PASSWORD = "pages/security/auth/password/forget-password";
    private static final String RESET_PASSWORD = "pages/security/auth/password/reset-password";

    private final PasswordResetService passwordResetService;
    //private final EmailService emailService;
   // private final PasswordEncoder passwordEncoder;

    public PasswordResetController(
        PasswordResetService passwordResetService,
        //EmailService emailService,
        PasswordEncoder passwordEncoder) {
        this.passwordResetService = passwordResetService;
       // this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/forget-password")
    public String forgetPassword() {
        return FORGET_PASSWORD;
    }

    @PostMapping("/forget-password")
    public String forgetPassword(
        @RequestParam("email") @Email(regexp = "\"^[\\\\w-\\\\.]+@([\\\\w-]+\\\\.)+[\\\\w-]{2,4}$\"") String email,
        RedirectAttributes redirect, Model model, HttpServletRequest request)
        throws EmailServiceException {

        final AuthUser user = passwordResetService.getUserByEmail(email);

        if (null == user) {
            model.addAttribute("error", email + " is not registered");
            return FORGET_PASSWORD;
        }

        final String token = UUID.randomUUID().toString();

        final PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(token);

        final PasswordResetToken savedToken = passwordResetService.save(resetToken);

        final EmailRequest tokenMail = new EmailRequest();
        tokenMail.setTo(user.getEmail());
        tokenMail.setSubject("Password Reset");

        final String url =
            "http://" + request.getServerName() + ":" + request.getServerPort() + request
                .getContextPath();

        tokenMail.setMessage(
            url + "/reset-password?id=" + user.getId() + "&token=" + savedToken.getToken());

       // emailService.send(tokenMail);

        redirect.addFlashAttribute("success",
            "An Email has been sent to reset your password. Please check your email.");

        return "redirect:/forget-password";
    }

    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("id") Long userId,
        @RequestParam("token") String token, Model model, RedirectAttributes redirect) {

        final PasswordResetToken resetToken = passwordResetService.getByToken(token);

        if (!userId.equals(resetToken.getUser().getId())) {
            redirect.addFlashAttribute("error", "Token is not registered for intended user");
            return "redirect:/forget-password";
        }

        if (resetToken.isExpired()) {
            redirect.addFlashAttribute("error", "Token has been expired. Request for new Token");
            return "redirect:/forget-password";
        }

        final ResetPassword password = new ResetPassword();
        password.setUserId(userId);
        password.setToken(token);

        model.addAttribute("dto", password);

        return RESET_PASSWORD;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("dto") ResetPassword password,
        RedirectAttributes redirect, Model model, BindingResult validationResult)
        throws EmailServiceException {

        if (validationResult.hasErrors()) {
            return RESET_PASSWORD;
        }

        final PasswordResetToken resetToken = passwordResetService.getByToken(password.getToken());

        final AuthUser user = resetToken.getUser();

        if (!password.getUserId().equals(resetToken.getUser().getId())) {
            redirect.addFlashAttribute("error",
                "Reset token has been interrupted, Please proceed with Password Reset Link");
            return "redirect:/forget-password";
        }

        user.setPassword(passwordEncoder.encode(password.getNewPassword()));

        if (passwordResetService.resetPassword(user)) {
            redirect.addFlashAttribute("success", "Your password has been changed.");

            final EmailRequest emailRequest = new EmailRequest();
            emailRequest.setTo(user.getEmail());
            emailRequest.setSubject("Password Changed");
            emailRequest.setMessage("Your password has been changed");

           // emailService.send(emailRequest);

            return "redirect:/login";
        }

        return RESET_PASSWORD;
    }
}*/
