package com.example.other.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangqi
 * @Package com.example.other
 * @Description: 时间日期工具类
 * @date 2019-07-05 11:36
 */
public class DateTimeUtils {

    public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    public static final String Y_M_D_H_M_STR = "yyyyMMddHHmmss";
    public static final String Y_M_D_H_M_S_SLASH = "yyyy/MM/dd HH:mm:ss";
    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String Y_M_D_STR = "yyyyMMdd";
    public static final String Y_M_D_SLASH = "yyyy/MM/dd";

    /**
     * 时间单元 毫秒
     */
    public static final int TIME_UNIT_MILLI = 1;
    /**
     * 时间单元 秒
     */
    public static final int TIME_UNIT_SECOND = 2;
    /**
     * 时间单元 分
     */
    public static final int TIME_UNIT_MINUTE = 3;
    /**
     * 时间单元 小时
     */
    public static final int TIME_UNIT_HOUR = 4;
    /**
     * 时间单元 天
     */
    public static final int TIME_UNIT_DAY = 5;
    /**
     * 时间单元 月
     */
    public static final int TIME_UNIT_MONTH = 6;
    /**
     * 时间单元 年
     */
    public static final int TIME_UNIT_YEAR = 7;


    /**
     * 默认格式化日期，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, Y_M_D_H_M_S);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
        return formatter.format(date.toInstant());
    }


    /**
     * 默认格式化日期(LocalDateTime类型)，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String formatDate(LocalDateTime date) {
        return formatDate(date, Y_M_D_H_M_S);
    }

    /**
     * 格式化日期(LocalDateTime类型)
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 默认日期字符串转换成日期，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @return
     */
    public static Date toDate(String dateStr) {
        return toDate(dateStr, Y_M_D_H_M_S);
    }

    /**
     * 日期字符串转换成日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date toDate(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
        if (!pattern.contains("HH")) {
            Instant instant = LocalDate.parse(dateStr, formatter).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        }
        return toDate(LocalDateTime.from(formatter.parse(dateStr)));
    }

    /**
     * LocalDateTime类型转化为Date
     *
     * @param localDateTime
     * @return
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate类型转化为Date
     *
     * @param localDate
     * @return
     */
    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * Date类型转化为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * Date类型转化为LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate toLocalDate(Date date) {
        return toLocalDateTime(date).toLocalDate();
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return toLocalDateTime(date).getYear();
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        return toLocalDateTime(date).getMonthValue();
    }

    /**
     * 获取天
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        return toLocalDateTime(date).getDayOfMonth();
    }

    /**
     * 获取小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        return toLocalDateTime(date).getHour();
    }

    /**
     * 获取分钟
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        return toLocalDateTime(date).getMinute();
    }

    /**
     * 获取秒数
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        return toLocalDateTime(date).getSecond();
    }

    /**
     * 获取年份
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static int getYear(String dateStr, String pattern) {
        return toLocalDateTime(toDate(dateStr, pattern)).getYear();
    }

    /**
     * 获取月份
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static int getMonth(String dateStr, String pattern) {
        return toLocalDateTime(toDate(dateStr, pattern)).getMonthValue();
    }

    /**
     * 获取天
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static int getDay(String dateStr, String pattern) {
        return toLocalDateTime(toDate(dateStr, pattern)).getDayOfMonth();
    }


    /**
     * 获取小时
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static int getHour(String dateStr, String pattern) {
        return toLocalDateTime(toDate(dateStr, pattern)).getHour();
    }

    /**
     * 获取分钟
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static int getMinute(String dateStr, String pattern) {
        return toLocalDateTime(toDate(dateStr, pattern)).getMinute();
    }


    /**
     * 获取秒数
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static int getSecond(String dateStr, String pattern) {
        return toLocalDateTime(toDate(dateStr, pattern)).getSecond();
    }

    /**
     * 获取月份的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        LocalDateTime with = toLocalDateTime(date).with(TemporalAdjusters.firstDayOfMonth());
        return toDate(with);
    }

    /**
     * 获取月份的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        LocalDateTime with = toLocalDateTime(date).with(TemporalAdjusters.lastDayOfMonth());
        return toDate(with);
    }

    /**
     * 获取一周第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        return toDate(toLocalDate(date).with(DayOfWeek.MONDAY));
    }

    /**
     * 获取一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        return toDate(toLocalDate(date).with(DayOfWeek.SUNDAY));
    }

    /**
     * 获取一天开始时间
     *
     * @param date
     * @return
     */
    public static Date getFirstTimeOfDay(Date date) {
        return setTimeOfDay(date, 0, 0, 0);
    }

    /**
     * 获取一天结束时间
     *
     * @param date
     * @return
     */
    public static Date getLastTimeOfDay(Date date) {
        return setTimeOfDay(date, 23, 59, 59);
    }


    /**
     * 获取两个时间间隔的所有日期
     *
     * @param startDate
     * @param endDate
     * @param isContainLast 是否保留最后一天
     * @return
     */
    public static List<Date> getIntervalDates(Date startDate, Date endDate, boolean isContainLast) {
        LocalDate startLocalDate = toLocalDate(startDate);
        LocalDate endLocalDate = toLocalDate(endDate);
        List<Date> intervals = new ArrayList<>();
        while (startLocalDate.isBefore(endLocalDate)) {
            intervals.add(toDate(startLocalDate));
            startLocalDate = startLocalDate.plusDays(1L);
        }
        if (isContainLast) {
            intervals.add(toDate(startLocalDate));
        }
        return intervals;
    }

    /**
     * 两个时间间隔秒数
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static long betweenSeconds(Date date, Date tarDate) {
        return ChronoUnit.SECONDS.between(toLocalDateTime(date), toLocalDateTime(tarDate));
    }

    /**
     * 两个时间间隔分钟数
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static long betweenMinutes(Date date, Date tarDate) {
        return Duration.between(toLocalDateTime(date), toLocalDateTime(tarDate)).toMinutes();
    }

    /**
     * 两个时间间隔小时数
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static long betweenHours(Date date, Date tarDate) {
        return Duration.between(toLocalDateTime(date), toLocalDateTime(tarDate)).toHours();
    }

    /**
     * 两个日期间隔天数
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static long betweenDays(Date date, Date tarDate) {
        return ChronoUnit.DAYS.between(toLocalDateTime(date), toLocalDateTime(tarDate));
    }


    /**
     * 两个日期间隔月数
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static long betweenMonths(Date date, Date tarDate) {
        return ChronoUnit.MONTHS.between(toLocalDate(date), toLocalDate(tarDate));
    }

    /**
     * 两个日期间隔年数
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static long betweenYears(Date date, Date tarDate) {
        return ChronoUnit.YEARS.between(toLocalDate(date), toLocalDate(tarDate));
    }


    /**
     * 增加时间
     *
     * @param date
     * @param timeUnit 时间单元 不支持毫秒数
     * @param num      正数，传负数也会变成正数
     * @return
     */
    public static Date plus(Date date, int timeUnit, long num) {
        LocalDateTime dateTime = toLocalDateTime(date);
        num = Math.abs(num);
        switch (timeUnit) {
            case TIME_UNIT_SECOND:
                dateTime = dateTime.plusSeconds(num);
                break;
            case TIME_UNIT_MINUTE:
                dateTime = dateTime.plusMinutes(num);
                break;
            case TIME_UNIT_HOUR:
                dateTime = dateTime.plusHours(num);
                break;
            case TIME_UNIT_DAY:
                dateTime = dateTime.plusDays(num);
                break;
            case TIME_UNIT_MONTH:
                dateTime = dateTime.plusMonths(num);
                break;
            case TIME_UNIT_YEAR:
                dateTime = dateTime.plusYears(num);
                break;
            default:
                break;
        }
        return toDate(dateTime);
    }

    /**
     * 减少时间
     *
     * @param date
     * @param timeUnit 时间单元 不支持毫秒数
     * @param num      正数，传负数也会变成正数
     * @return
     */
    public static Date minus(Date date, int timeUnit, long num) {
        LocalDateTime dateTime = toLocalDateTime(date);
        num = Math.abs(num);
        switch (timeUnit) {
            case TIME_UNIT_SECOND:
                dateTime = dateTime.minusSeconds(num);
                break;
            case TIME_UNIT_MINUTE:
                dateTime = dateTime.minusMinutes(num);
                break;
            case TIME_UNIT_HOUR:
                dateTime = dateTime.minusHours(num);
                break;
            case TIME_UNIT_DAY:
                dateTime = dateTime.minusDays(num);
                break;
            case TIME_UNIT_MONTH:
                dateTime = dateTime.minusMonths(num);
                break;
            case TIME_UNIT_YEAR:
                dateTime = dateTime.minusYears(num);
                break;
            default:
                break;
        }
        return toDate(dateTime);
    }

    /**
     * 比较时间 与目标时间早返回true
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static boolean isBefore(Date date, Date tarDate) {
        return toLocalDateTime(date).isBefore(toLocalDateTime(tarDate));
    }

    /**
     * 比较时间 与目标时间晚返回true
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static boolean isAfter(Date date, Date tarDate) {
        return toLocalDateTime(date).isAfter(toLocalDateTime(tarDate));
    }


    /**
     * 比较时间 与目标时间相等返回true
     *
     * @param date
     * @param tarDate
     * @return
     */
    public static boolean isEqual(Date date, Date tarDate) {
        return toLocalDateTime(date).isEqual(toLocalDateTime(tarDate));
    }


    /**
     * 设置时间
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date setTimeOfDay(Date date, int hour, int minute, int second) {
        LocalDateTime localDateTime = toLocalDateTime(date);
        return toDate(localDateTime.withHour(hour).withMinute(minute).withSecond(second));
    }

    public static List<Date> getHalfTimes(Date startDate, Date endDate) {
        LocalDateTime startTime = toLocalDateTime(startDate);
        LocalDateTime endTime = toLocalDateTime(endDate);
        if (startTime.getMinute() < 30) {
            startTime = startTime.withMinute(0);
        } else {
            startTime = startTime.withMinute(30);
        }
        List<Date> times = new ArrayList<>();
        while (startTime.isBefore(endTime)) {
            times.add(toDate(startTime));
            startTime = startTime.plusMinutes(30);
        }
        return times;
    }

    public static void main(String[] args) {
        Date date = DateTimeUtils.toDate("2019-09-26 00:00:00");
        Date date1 = DateTimeUtils.toDate("2019-09-27 00:01:00");


        System.out.println(getHalfTimes(date
                , date1).size());
    }

}
