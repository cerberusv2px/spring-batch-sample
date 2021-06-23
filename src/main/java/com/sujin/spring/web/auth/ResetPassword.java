package com.sujin.spring.web.auth;

import com.sujin.spring.service.validator.notemptyvalue.NotEmptyValue;
import com.sujin.spring.service.validator.verifyproperty.VerifyProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@VerifyProperty(
    property = "newPassword",
    verifyWith = "verifyPassword",
    message = "Password does not match"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ResetPassword {

    private Long userId;

    private String token;

    @NotEmptyValue
    private String newPassword;

    @NotEmptyValue
    private String verifyPassword;
}
