package com.example.other.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huangqi
 * @Package com.example.other.utils
 * @Description:
 * @date 2019-07-16 15:30
 */
public class RegexUtils {
	public static Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");

	/**
	 * 是否为数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		return Pattern.matches("^\\d+$", str);
	}

	/**
	 * 是否为全英文
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEnglish(String str) {
		return Pattern.matches("^[A-Za-z]+$", str);
	}

	/**
	 * 是否为全英文
	 *
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {
		Matcher matcher = CHINESE_PATTERN.matcher(str);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isNumber("sad"));
		System.out.println(isEnglish("sad1"));
		System.out.println(isContainChinese("1231打算&  打算"));
	}
}
