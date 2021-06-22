package com.sujin.spring.core.utils;

public class StringExportUtils {

    private StringExportUtils() {

    }

    public static String removeClRf(String value) {
        if (null == value) {
            return "";
        }
        return value.replaceAll("[\\r\\n\\t]", " ");
    }
}
