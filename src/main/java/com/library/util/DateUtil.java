package com.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{
	private DateUtil() {
		
	}

	/**
	 * Performs addition on the given date.
	 * 
	 * @param date - An instance of {@link Date} contains date to be modified.
	 * @param days - Number of days to be added.
	 * @return {@link Date}
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}
	
	/**
	 * Converts String to Date
	 * @param date - String date to be parsed.
	 * @return A {@link Date} parsed from the string.
	 */
	public static Date toDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
