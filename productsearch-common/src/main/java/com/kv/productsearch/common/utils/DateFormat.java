package com.kv.productsearch.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: kungwang
 * Date: 9/2/12
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateFormat {


    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd_HH:mm:ss";

    public static Date getDate(String date) throws ParseException {
        if (StringUtils.isEmpty(date)) {
            throw new IllegalArgumentException("Date attribute should not be empty.");
        }

        return new SimpleDateFormat(DATE_FORMAT_PATTERN).parse(date);
    }

    public static String getStringDate(Date date) throws ParseException {
        if (date == null) {
            throw new IllegalArgumentException("Date attribute should not be empty.");
        }

        return new SimpleDateFormat(DATE_FORMAT_PATTERN).format(date);
    }

}
