package com.sujin.spring.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String REPORT_NAME_FORMAT = "yyyyMMdd";
    private static final String REPORT_DIRECTORY_FORMAT = "yyyyMM";

    private DateFormatUtils() {
    }

    public static String format(Date date) {
        if (null == date) {
            return null;
        }

        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }

    public static String formatForFileName(Date date) {
        return new SimpleDateFormat(REPORT_NAME_FORMAT).format(date);
    }

    public static String formatForDirectory(Date date) {
        return new SimpleDateFormat(REPORT_DIRECTORY_FORMAT).format(date);
    }

    /*public static String formatDateTime(DateTime dateTime) {
        return dateTime.toString(DATE_TIME_FORMAT);
    }*/
}
