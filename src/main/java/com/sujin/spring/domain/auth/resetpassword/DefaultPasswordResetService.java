package com.sujin.spring.domain.auth.resetpassword;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.sujin.spring.domain.auth.AuthUser;
import com.sujin.spring.domain.auth.AuthUserRepository;

@Service
public class DefaultPasswordResetService implements PasswordResetService {

    private final AuthUserRepository userRepository;
    private final PasswordResetTokenRepository repository;

    private final int tokenExpiryInMinutes = 3600;

    public DefaultPasswordResetService(
        AuthUserRepository userRepository,
        PasswordResetTokenRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
        // this.tokenExpiryInMinutes = properties.getResetTokenExpiryInMinutes();
    }

    @Override
    public AuthUser getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public PasswordResetToken save(PasswordResetToken resetToken) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, tokenExpiryInMinutes);

        final Date expiryDate = new Date(cal.getTime().getTime());

        resetToken.setExpiryDate(expiryDate);

        return repository.save(resetToken);
    }

    @Override
    public PasswordResetToken getByToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    public boolean resetPassword(AuthUser u) {
        return null != userRepository.save(u);
    }
}
