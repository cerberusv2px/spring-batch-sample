package com.sujin.spring.service.validator.cornexpresssion;

import java.text.ParseException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronExpression;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class CronFieldsValidator implements ConstraintValidator<CronFields, Object> {

    private String second;
    private String minute;
    private String hour;
    private String dayOfMonth;
    private String month;
    private String dayOfWeek;
    private String year;

    @Override
    public void initialize(CronFields constraintAnnotation) {

        second = constraintAnnotation.second();
        minute = constraintAnnotation.minute();
        hour = constraintAnnotation.hour();
        dayOfMonth = constraintAnnotation.dayOfMonth();
        month = constraintAnnotation.month();
        dayOfWeek = constraintAnnotation.dayOfWeek();
        year = constraintAnnotation.year();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        final BeanWrapper wrapper = new BeanWrapperImpl(o);
        String secondValue = (String) wrapper.getPropertyValue(this.second);
        String minuteValue = (String) wrapper.getPropertyValue(this.minute);
        String hourValue = (String) wrapper.getPropertyValue(this.hour);
        String dayOfMonthValue = (String) wrapper.getPropertyValue(this.dayOfMonth);
        String monthValue = (String) wrapper.getPropertyValue(this.month);
        String dayOfWeekValue = (String) wrapper.getPropertyValue(this.dayOfWeek);
        String yearValue = (String) wrapper.getPropertyValue(this.year);

        final String expression = StringUtils
            .joinWith(" ", secondValue, minuteValue, hourValue, dayOfMonthValue, monthValue,
                dayOfWeekValue, yearValue);

        try {
            CronExpression.validateExpression(expression);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
