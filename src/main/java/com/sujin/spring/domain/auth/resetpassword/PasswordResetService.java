package com.sujin.spring.domain.auth.resetpassword;

import com.sujin.spring.domain.auth.AuthUser;

public interface PasswordResetService {

    public AuthUser getUserByEmail(String email);

    public PasswordResetToken save(PasswordResetToken resetToken);

    PasswordResetToken getByToken(String token);

    public boolean resetPassword(AuthUser u);
}
