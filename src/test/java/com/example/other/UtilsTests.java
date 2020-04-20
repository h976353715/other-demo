package com.example.other;

import com.example.other.utils.DateTimeUtils;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class UtilsTests {


	@Test
	public void contextLoads() throws Exception {
		System.out.println(DateTimeUtils.formatDate(new Date()));
		System.out.println(DateTimeUtils.formatDate(new Date(), DateTimeUtils.Y_M_D_SLASH));
		System.out.println(DateTimeUtils.formatDate(new Date(), DateTimeUtils.Y_M_D));
		System.out.println(DateTimeUtils.formatDate(LocalDateTime.now()));
		System.out.println(DateTimeUtils.toDate("2019-02-16", DateTimeUtils.Y_M_D));
		System.out.println(DateTimeUtils.toDate("2019-02-16 15:33:36"));
		System.out.println(DateTimeUtils.toDate(LocalDate.now()));
		System.out.println(DateTimeUtils.toLocalDate(new Date()));
		System.out.println();
		System.out.println(DateTimeUtils.getYear(new Date()));
		System.out.println(DateTimeUtils.getMonth(new Date()));
		System.out.println(DateTimeUtils.getDay(new Date()));
		System.out.println(DateTimeUtils.getHour(new Date()));
		System.out.println(DateTimeUtils.getMinute(new Date()));
		System.out.println(DateTimeUtils.getSecond(new Date()));
		System.out.println(DateTimeUtils.getYear("2019-02-16 15:33:36", DateTimeUtils.Y_M_D_H_M_S));
		System.out.println(DateTimeUtils.getMonth("2019-02-16 15:33:36", DateTimeUtils.Y_M_D_H_M_S));
		System.out.println(DateTimeUtils.getDay("2019-02-16 15:33:36", DateTimeUtils.Y_M_D_H_M_S));
		System.out.println(DateTimeUtils.getHour("2019-02-16 15:33:36", DateTimeUtils.Y_M_D_H_M_S));
		System.out.println(DateTimeUtils.getMinute("2019-02-16 15:33:36", DateTimeUtils.Y_M_D_H_M_S));
		System.out.println(DateTimeUtils.getSecond("2019-02-16", DateTimeUtils.Y_M_D));
		System.out.println(DateTimeUtils.getFirstDayOfMonth(new Date()));
		System.out.println(DateTimeUtils.getLastDayOfMonth(new Date()));
		System.out.println(DateTimeUtils.getFirstDayOfWeek(new Date()));
		System.out.println(DateTimeUtils.getLastDayOfWeek(new Date()));
		System.out.println(DateTimeUtils.getFirstTimeOfDay(new Date()));
		System.out.println(DateTimeUtils.getLastTimeOfDay(new Date()));

		System.out.println();
		Date startDate = DateTimeUtils.toDate("2019-02-16 15:33:37");
		Date endDate = DateTimeUtils.toDate("2019-02-20 15:40:36");
		System.out.println(DateTimeUtils.betweenSeconds(startDate, endDate));
		System.out.println(DateTimeUtils.betweenMinutes(startDate, endDate));
		System.out.println(DateTimeUtils.betweenHours(startDate, endDate));
		System.out.println(DateTimeUtils.betweenDays(startDate, endDate));
		System.out.println(DateTimeUtils.betweenMonths(startDate, endDate));
		System.out.println(DateTimeUtils.betweenYears(startDate, endDate));
		System.out.println();
		System.out.println(DateTimeUtils.plus(startDate, DateTimeUtils.TIME_UNIT_SECOND,3));
		System.out.println(DateTimeUtils.plus(startDate, DateTimeUtils.TIME_UNIT_MINUTE,3));
		System.out.println(DateTimeUtils.plus(startDate, DateTimeUtils.TIME_UNIT_HOUR,3));
		System.out.println(DateTimeUtils.plus(startDate, DateTimeUtils.TIME_UNIT_DAY,3));
		System.out.println(DateTimeUtils.plus(startDate, DateTimeUtils.TIME_UNIT_MONTH,3));
		System.out.println(DateTimeUtils.plus(startDate, DateTimeUtils.TIME_UNIT_YEAR,3));
		System.out.println();
		System.out.println(DateTimeUtils.minus(startDate, DateTimeUtils.TIME_UNIT_SECOND,3));
		System.out.println(DateTimeUtils.minus(startDate, DateTimeUtils.TIME_UNIT_MINUTE,3));
		System.out.println(DateTimeUtils.minus(startDate, DateTimeUtils.TIME_UNIT_HOUR,3));
		System.out.println(DateTimeUtils.minus(startDate, DateTimeUtils.TIME_UNIT_DAY,3));
		System.out.println(DateTimeUtils.minus(startDate, DateTimeUtils.TIME_UNIT_MONTH,3));
		System.out.println(DateTimeUtils.minus(startDate, DateTimeUtils.TIME_UNIT_YEAR,3));
		System.out.println();
		System.out.println(DateTimeUtils.isAfter(startDate, endDate));
		System.out.println(DateTimeUtils.isBefore(startDate, endDate));
		System.out.println(DateTimeUtils.isEqual(startDate, endDate));
		System.out.println();
		System.out.println(DateTimeUtils.getIntervalDates(startDate, endDate,false));
		System.out.println(DateTimeUtils.getIntervalDates(startDate, endDate,true));


	}

	@Test
	public void test() throws Exception {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
//		LocalDateTime from = LocalDateTime.from(formatter.parse("2019-02-16 14:35:22"));
	/*	LocalDateTime now = LocalDateTime.now();
		LocalDateTime localDateTime = now.withHour(23);
				.withMinute(59)
				.withSecond(59)
				.withNano(999999999);*//*
		System.out.println(localDateTime);*/

		Date startDate = DateTimeUtils.toDate("2019-02-16 15:33:37");
		Date endDate = DateTimeUtils.toDate("2019-02-16 15:33:37");

		LocalDateTime from = LocalDateTime.from(formatter.parse("2019-02-16 15:33:37"));
		LocalDateTime to = LocalDateTime.from(formatter.parse("2029-02-17 15:40:36"));
		Date date = new Date();
		Instant instant = date.toInstant();

		Date fromInstant = Date.from(instant);
		int months = Period.between(from.toLocalDate(), to.toLocalDate()).getMonths();
		int days = Period.between(from.toLocalDate(), to.toLocalDate()).getDays();
		long between = ChronoUnit.MONTHS.between(from, to);
		long days2 = ChronoUnit.DAYS.between(from, to);
		System.out.println(months);
		System.out.println(days);
		System.out.println(between);
		System.out.println(days2);
		System.out.println(Duration.between(from,to).toDays());
		System.out.println(ChronoUnit.HOURS.between(from,to));

	}

	@Test
	public void testD(){
		System.out.println(Instant.now());
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		System.out.println(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toLocalDateTime());

		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
		Instant.from(formatter.parse("2019-07-03"));
	}

}
