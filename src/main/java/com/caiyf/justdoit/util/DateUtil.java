package com.caiyf.justdoit.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author caiyf
 * @date 2019-11-17
 * @desc date util
 */
public class DateUtil {

    public static final String DATE_FMT_0 = "yyyy-MM-dd";
    public static final String DATE_FMT_1 = "yyyy/MM/dd";
    public static final String DATE_FMT_2 = "yyyyMMdd";
    public static final String DATE_FMT_3 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FMT_4 = "yyyyMMddHHmmss";
    private static final String ERROR_LOG_MSG = "[date-util-err]:";
    private static final Integer SEC_MILLIS_NUM = 1000;
    private static final Integer DAY_OF_SEC = 60 * 60 * 24;
    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * private constructor
     */
    private DateUtil() {}

    /**
     * format date to pattern string
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        String dateString = null;
        try {
            DateTime dateTime = new DateTime(date);
            dateString = dateTime.toString(pattern);
        } catch (Exception e) {
            log.error("{} format date fail, date: {}, pattern: {}， err-msg: {}", ERROR_LOG_MSG, date, pattern, e.getMessage());
        }
        return dateString;
    }

    /**
     * parse pattern string to date
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date parse(String dateString, String pattern) {
        Date date = null;
        try {
            DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
            date = format.parseLocalDateTime(dateString).toDate();
        } catch (Exception e) {
            log.error("{} parse date fail, date: {}, pattern: {}, err-msg: {}", ERROR_LOG_MSG, date, pattern, e.getMessage());
        }
        return date;
    }

    /**
     * get date of start, eg: 2019-11-17 11:11:11 -> 2019-11-17 00:00:01
     *
     * @param date
     * @return
     */
    public static Date getStartTime(Date date) {
        Date startDate = null;
        try {
            DateTime dateTime = new DateTime(date);
            startDate = dateTime.secondOfDay().withMinimumValue().toDate();
        } catch (Exception e) {
            log.error("{} get start date fail, date: {}, err-msg: {}", ERROR_LOG_MSG, date, e.getMessage());
        }
        return startDate;
    }

    /**
     * get date of end, eg: 2019-11-17 11:11:11 -> 2019-11-17 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Date endDate = null;
        try {
            DateTime dateTime = new DateTime(date);
            endDate = dateTime.secondOfDay().withMaximumValue().toDate();
        } catch (Exception e) {
            log.error("{} get end date fail, date: {}, err-msg: {}", ERROR_LOG_MSG, date, e.getMessage());
        }
        return endDate;
    }

    /**
     * get millis of date
     *
     * @param date
     * @return
     */
    public static Long getDateMillis(Date date) {
        Long millis = null;
        try {
            DateTime dateTime = new DateTime(date);
            millis = dateTime.getMillis();
        } catch (Exception e) {
            log.error("{} get date millis fail, date: {}, err-msg: {}", ERROR_LOG_MSG, date, e.getMessage());
        }
        return millis;
    }

    /**
     * millis to date string
     *
     * @param millis
     * @param pattern
     * @return
     */
    public static String formatDateByMillis(Long millis, String pattern) {
        String dateString = null;
        try {
            DateTime dateTime = new DateTime(millis);
            dateString = format(dateTime.toDate(), pattern);
        } catch (Exception e) {
            log.error("{} format date by millis fail, millis: {}, pattern: {}, err-msg: {}", ERROR_LOG_MSG, millis, pattern, e.getMessage());
        }
        return dateString;
    }

    /**
     * get current year, eg: 2019
     *
     * @return
     */
    public static Integer getCurrentYear() {
        DateTime dateTime = new DateTime();
        return dateTime.getYear();
    }

    /**
     * get current month, eg: 11
     *
     * @return
     */
    public static Integer getCurrentMonth() {
        DateTime dateTime = new DateTime();
        return dateTime.getMonthOfYear();
    }

    /**
     * get current day of month, eg: 17
     *
     * @return
     */
    public static Integer getCurrentDay() {
        DateTime dateTime = new DateTime();
        return dateTime.getDayOfMonth();
    }

    /**
     * get diff seconds between start date and end date
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDiffSecBetweenDate(Date startDate, Date endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            log.error("{} compile date fail, null date for start date: {}, end date: {}", ERROR_LOG_MSG, startDate, endDate);
        }
        return (startDate.getTime() - endDate.getTime()) / SEC_MILLIS_NUM;
    }

    /**
     *  get diff days between start date and end date
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer getDiffDayBetweenDate(Date startDate, Date endDate) {
        Long intervalSec = getDiffSecBetweenDate(startDate, endDate);
        return intervalSec.intValue() / DAY_OF_SEC;
    }

    /**
     * incr appoint days
     *
     * @param sourceDate
     * @param days
     * @return
     */
    public static Date incrDays(Date sourceDate, Integer days) {
        Calendar targetDate = Calendar.getInstance();
        targetDate.setTime(sourceDate);
        targetDate.set(Calendar.DATE, targetDate.get(Calendar.DATE), days);
        return targetDate.getTime();
    }

    /**
     * incr appoint hours
     *
     * @param sourceDate
     * @param hours
     * @return
     */
    public static Date incrHours(Date sourceDate, Integer hours) {
        Calendar targetDate = Calendar.getInstance();
        targetDate.setTime(sourceDate);
        targetDate.set(Calendar.HOUR, targetDate.get(Calendar.HOUR), hours);
        return targetDate.getTime();
    }

}
