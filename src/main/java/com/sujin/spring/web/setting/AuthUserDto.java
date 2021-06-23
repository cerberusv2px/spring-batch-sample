package com.sujin.spring.web.setting;

import javax.validation.constraints.Email;

import com.sujin.spring.core.BaseDto;
import com.sujin.spring.service.validator.notemptyvalue.NotEmptyValue;
import com.sujin.spring.service.validator.verifyproperty.VerifyProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@VerifyProperty(
    property = "password",
    verifyWith = "verifyPassword",
    message = "Password does not match"
)
public class AuthUserDto extends BaseDto<Long> {

    @NotEmptyValue
    private String name;

    @NotEmptyValue
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotEmptyValue
    private String username;

    @NotEmptyValue
    private String password;

    @NotEmptyValue
    private String verifyPassword;

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
}
