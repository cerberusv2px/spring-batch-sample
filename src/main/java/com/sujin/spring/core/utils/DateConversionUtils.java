package com.sujin.spring.core.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateConversionUtils {

    private DateConversionUtils() {
    }

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.of("Asia/Kathmandu")).toLocalDate();
    }

    public static Date convertToDate(LocalDate date) {
        return Date.from(date.atStartOfDay().atZone(ZoneId.of("Asia/Kathmandu")).toInstant());
    }

    public static Date getPreviousDay(Date date) {
        LocalDate localDate = convertToLocalDate(date).minusDays(1);
        return convertToDate(localDate);
    }

    public static String getDateValue(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
