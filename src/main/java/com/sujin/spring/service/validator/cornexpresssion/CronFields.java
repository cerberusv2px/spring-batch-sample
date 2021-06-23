package com.sujin.spring.service.validator.cornexpresssion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CronFieldsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CronFields {

    String message() default "Invalid Cron field values";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String second();

    String minute();

    String hour();

    String dayOfMonth();

    String month();

    String dayOfWeek();

    String year();
}
