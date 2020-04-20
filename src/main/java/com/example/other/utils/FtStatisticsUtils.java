package com.example.other.utils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author huangqi
 * @Package com.zm.ft.utils
 * @Description:
 * @date 2019-09-26 16:47
 */
public class FtStatisticsUtils {

    /**
     * 获取半小时统计的时间开始 比如13：05 进行统计 此时返回 12：30
     *
     * @param date
     * @return
     */
    public static Date getStartHalfTime(Date date) {
        LocalDateTime nowTime = DateTimeUtils.toLocalDateTime(date);
        LocalDateTime compareTime = DateTimeUtils.toLocalDateTime(date).withMinute(30).withSecond(0);
        LocalDateTime time = null;
        if (nowTime.isBefore(compareTime)) {
            time = nowTime.withMinute(0).withSecond(0).minusMinutes(30L);
        } else {
            time = nowTime.withMinute(30).withSecond(0).minusMinutes(30L);
        }
        System.out.println(DateTimeUtils.formatDate(time));
        return DateTimeUtils.toDate(time);
    }

    /**
     * 获取半小时统计的时间结束  比如13：05 进行统计 此时返回 13：00
     *
     * @param date
     * @return
     */
    public static Date getEndHalfTime(Date date) {
        Date time = DateTimeUtils.plus(getStartHalfTime(date), DateTimeUtils.TIME_UNIT_MINUTE, 30);
        System.out.println(DateTimeUtils.formatDate(time));
        return time;
    }

    public static void main(String[] args) {
        getStartHalfTime(DateTimeUtils.toDate("2019-09-26 16:05:00"));
        getEndHalfTime(DateTimeUtils.toDate("2019-09-26 16:05:00"));
        System.out.println(DateTimeUtils.formatDate(new Date(),"HHmm") );
        System.out.println( LocalDateTime.now().getMinute());

    }

}
