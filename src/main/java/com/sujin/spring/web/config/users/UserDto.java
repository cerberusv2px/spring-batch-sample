package com.sujin.spring.web.config.users;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.sujin.spring.core.BaseDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto<Long> {

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String dob;
}
