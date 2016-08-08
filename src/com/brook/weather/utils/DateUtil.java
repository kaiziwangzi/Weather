package com.brook.weather.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat mDefaultFormat = new SimpleDateFormat();

	public static String getServerDate(Date date) {
		return format("yyyy-MM-dd", date);
	}

	public static String format(String pattern, Date date) {
		if (date == null) {
			return null;
		}
		mDefaultFormat.applyPattern(pattern);
		return mDefaultFormat.format(date);
	}

	public static String format(String date) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat formatter1 = null;
		SimpleDateFormat formatter = null;
		try {
			formatter1 = new SimpleDateFormat("yyyyMMddHHmmss");
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return formatter.format(formatter1.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
}
