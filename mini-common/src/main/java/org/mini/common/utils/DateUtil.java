package org.mini.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	//private static final int[] day31 = { 1, 3, 5, 7, 8, 10, 12 };
	
	private DateUtil() {
		
	}
	
	/**
	 * 返回指定年指定月份的天数
	 * @param year :年份
	 * @param month :月份
	 * @return 当月总天数
	 */
	public static int getTotalDaysOfMonth(int year, int month) {
		try {
			//Date date = parse("yyyy-MM", String.format("{0}-{2}", year, month));
			Calendar cal = Calendar.getInstance();
			cal.set(year, month, 0);
			return cal.get(Calendar.DAY_OF_MONTH);
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}

		/*
		if (month == 2) {

			if ((year / 4 == 0 && year / 100 != 0) || year / 400 == 0) {
				return 29;
			} else
				return 28;
		}
		
		if(Arrays.asList(day31).contains(month)) {
			return 31;
		}

		return 30;
		*/
	}
	
	public static Date parse(String format, String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.parse(dateString);
	}
}
