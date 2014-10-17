package com.boe.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.ant.util.DateUtils;

public class DateUtil {

	public static final String BUNDLE_KEY = "ApplicationResources";
	private static final String TIME_PATTERN = "HH:mm";
	private static SimpleDateFormat chineseDateFormat;
	private static Log log = LogFactory.getLog(DateUtils.class);
	private int lunarDay;
	private static int[] lunarInfo;
	private boolean lunarLeap;
	private int lunarMonth;
	private int lunarYear;

	static {
		chineseDateFormat = new SimpleDateFormat("yyyy年MM月dd日");

		lunarInfo = new int[] { 19416, 19168, 42352, 21717, 53856, 55632,
				91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381,
				46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216,
				27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984,
				28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432,
				120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423,
				27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728,
				27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632,
				23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616,
				46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216,
				27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984,
				27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888,
				30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780,
				44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296,
				44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200,
				30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645,
				46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936,
				44448, 84835 };
	}

	public static Date GetAdjustDate(Long tzAdjust, Date date) {
		Date tmpDate = new Date();
		tmpDate.setTime(date.getTime() + hoursToMinSec(tzAdjust.intValue()));
		return tmpDate;
	}

	public static Date GetAdjustDate(Long tzAdjust) {
		Date date = getGreenwichDate();
		date.setTime(date.getTime() + hoursToMinSec(tzAdjust.intValue()));
		return date;
	}

	public static long addDays(long longCalendar, int intDay) {
		long longDate = 0L;
		longDate = longCalendar + intDay * 86400000;
		return longDate;
	}

	public static long addHours(long longCalendar, int intHour) {
		long longDate = 0L;
		longDate = longCalendar + intHour * 3600000;
		return longDate;
	}

	public static long addMinutes(long longCalendar, int intMin) {
		try {
			long longDate = 0L;
			longDate = longCalendar + intMin * 60000;
			return longDate;
		} catch (Exception Exp) {
		}
		return -1L;
	}

	public static Calendar convertDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static String convertDateToString(Date date, Long tzAdjust) {
		Date tmpDate = getGreenwichDate();
		tmpDate.setTime(date.getTime() + hoursToMinSec(tzAdjust.intValue()));
		return getDateTime(getDatePattern(), tmpDate);
	}

	public static String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static String convertDateToString(String aMask, Date aDate) {
		return getDateTime(aMask, aDate);
	}

	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;
		try {
			if (log.isDebugEnabled()) {
				log.info("converting date with pattern: " + getDatePattern());
			}
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");

			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return aDate;
	}

	public static Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(aMask);
		if (log.isDebugEnabled())
			log.info("converting '" + strDate + "' to date with mask '" + aMask
					+ "'");
		Date date;
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return date;
	}

	public static int getDate(Calendar c) {
		if (c != null) {
			return c.get(5);
		}

		return Calendar.getInstance().get(5);
	}

	public static String getDate(Date aDate) {
		String returnValue = "";
		if (aDate != null) {
			SimpleDateFormat df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}
		return returnValue;
	}

	public static String getDatePattern() {
		String defaultDatePattern;
		try {
			defaultDatePattern = ResourceBundle.getBundle(
					"ApplicationResources").getString("date.format");
		} catch (MissingResourceException mse) {
			defaultDatePattern = "MM/dd/yyyy";
		}
		return defaultDatePattern;
	}

	public static String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";
		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return returnValue;
	}

	public static String getDateTimePattern() {
		return getDatePattern() + " HH:mm:ss.S";
	}

	public static int getDay(Calendar c) {
		if (c != null) {
			return c.get(7);
		}
		return Calendar.getInstance().get(7);
	}

	public static long getDistinceDay(Date beforedate, Date afterdate) {
		long dayCount = 0L;
		try {
			dayCount = (afterdate.getTime() - beforedate.getTime()) / 86400000L;
		} catch (Exception e) {
			log.info("Date parse error!");
		}
		return dayCount;
	}

	public static long getDistinceDay(String beforedate, String afterdate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0L;
		try {
			Date d1 = df.parse(beforedate);
			Date d2 = df.parse(afterdate);
			dayCount = (d2.getTime() - d1.getTime()) / 86400000L;
		} catch (ParseException e) {
			log.info("Date parse error!");
		}
		return dayCount;
	}

	public static long getDistinceMinute(Date beforeDateTime, Date afterDateTime)
			throws Exception {
		long minutesCount = 0L;
		try {
			minutesCount = (afterDateTime.getTime() - beforeDateTime.getTime()) / 3600L;
		} catch (Exception e) {
			log.info("Date parse error!");
			throw e;
		}
		return minutesCount;
	}

	public static long getDistinceMinute(String beforeDateTime,
			String afterDateTime) throws Exception {
		long minutesCount = 0L;
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = d.parse(beforeDateTime);
			Date d2 = d.parse(afterDateTime);
			minutesCount = (d2.getTime() - d1.getTime()) / 3600L;
		} catch (ParseException e) {
			log.info("Date parse error!");
			throw e;
		}
		return minutesCount;
	}

	public static long getDistinceMonth(String beforedate, String afterdate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		long monthCount = 0L;
		try {
			Date d1 = df.parse(beforedate);
			Date d2 = df.parse(afterdate);
			monthCount = (d2.getYear() - d1.getYear()) * 12 + d2.getMonth()
					- d1.getMonth();
		} catch (ParseException e) {
			log.info("Date parse error!");
		}
		return monthCount;
	}

	public static long getDistinceTime(Date beforeDateTime) throws Exception {
		return getDistinceTime(beforeDateTime, getGreenwichDate());
	}

	public static long getDistinceTime(String beforeDateTime) throws Exception {
		return getDistinceTime(beforeDateTime,
				convertDateToString(getGreenwichDate()));
	}

	public static long getDistinceTime(Date beforeDateTime, Date afterDateTime)
			throws Exception {
		long timeCount = 0L;
		try {
			timeCount = (afterDateTime.getTime() - beforeDateTime.getTime()) / 3600000L;
		} catch (Exception e) {
			log.info("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	public static long getDistinceTime(String beforeDateTime,
			String afterDateTime) throws Exception {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeCount = 0L;
		try {
			Date d1 = d.parse(beforeDateTime);
			Date d2 = d.parse(afterDateTime);
			timeCount = (d2.getTime() - d1.getTime()) / 3600000L;
		} catch (ParseException e) {
			log.info("Date parse error!");
			throw e;
		}
		return timeCount;
	}

	public static int getDistinceYear(String beforedate, String afterdate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int monthCount = 0;
		try {
			Date d1 = df.parse(beforedate);
			Date d2 = df.parse(afterdate);
			monthCount = d2.getYear() - d1.getYear();
		} catch (ParseException e) {
			log.info("Date parse error!");
		}
		return monthCount;
	}

	public static Date getGreenwichDate() {
		Date date = new Date();
		try {
			TimeZone tz = TimeZone.getTimeZone("Etc/Greenwich");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(tz);
			String date_tz = sdf.format(date);
			sdf.setTimeZone(TimeZone.getDefault());
			return sdf.parse(date_tz);
		} catch (ParseException e) {
		}
		return date;
	}

	public static int getHour(Calendar c) {
		if (c != null) {
			return c.get(10);
		}

		return Calendar.getInstance().get(10);
	}

	public int getLeapDays(int year) {
		if (getLeapMonth(year) > 0) {
			return (lunarInfo[(year - 1900)] & 0x10000) > 0 ? 30 : 29;
		}

		return 0;
	}

	public int getLeapMonth(int year) {
		return lunarInfo[(year - 1900)] & 0xF;
	}

	public int getLunarDay() {
		return this.lunarDay;
	}

	public boolean getLunarLeap() {
		return this.lunarLeap;
	}

	public int getLunarMonth() {
		return this.lunarMonth;
	}

	public int getLunarYear() {
		return this.lunarYear;
	}

	public static int getMinute(Calendar c) {
		if (c != null) {
			return c.get(12);
		}

		return Calendar.getInstance().get(12);
	}

	public static int getMonth(Calendar c) {
		if (c != null) {
			return c.get(2);
		}

		return Calendar.getInstance().get(2);
	}

	public int getMonthDays(int year, int month) {
		return (lunarInfo[(year - 1900)] & 65536 >> month) > 0 ? 30 : 29;
	}

	public static List getMonthList(Date endDate, int dayth) {
		List needList = new ArrayList();
		Calendar next = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String thisDate = sdf.format(new Date());
		String nextDate = sdf.format(next.getTime());

		while ((next.getTime().compareTo(endDate) <= 0)
				|| (thisDate.equals(nextDate))) {
			int nextWeek = next.get(5);
			if (nextWeek == dayth) {
				needList.add(sdf.format(next.getTime()));
			}
			next.add(5, 1);
			nextDate = sdf.format(next.getTime());
		}
		return needList;
	}

	public int getScan(int minYear, int minMonth, int minDay, boolean isLeap,
			int maxYear, int maxMonth, int maxDay) {
		int offset = 0;
		int yearScan = maxYear - minYear;
		int i;
		if (yearScan > 0) {
			for (i = minMonth; i <= 12; i++) {
				offset += getMonthDays(minYear, i);
			}
			offset -= minDay;

			if ((!isLeap) && (getLeapMonth(minYear) >= minMonth)) {
				offset += getLeapDays(minYear);
			}

			for (i = 1; i < yearScan; i++) {
				offset += getYearDays(minYear + i);
			}

			for (i = 1; i < maxMonth; i++) {
				offset += getMonthDays(maxYear, i);
			}

			if (getLeapMonth(maxYear) < maxMonth) {
				offset += getLeapDays(maxYear);
			}
			offset += maxDay;
		} else {
			for (i = minMonth; i < maxMonth; i++) {
				offset += getMonthDays(minYear, i);
			}
			int leap = getLeapMonth(minYear);
			if ((!isLeap) && (leap >= minMonth) && (leap < maxMonth)) {
				offset += getLeapDays(maxYear);
			}
			offset -= minDay;
			offset += maxDay;
		}
		return offset;
	}

	public static Date getSystemTime() {
		Calendar cldCurrent = Calendar.getInstance();
		Date currentDate = cldCurrent.getTime();
		return currentDate;
	}

	public static String getTimeNow(Date theTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String date_tz = sdf.format(theTime);
		return date_tz;
	}

	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));
		return cal;
	}

	public static String getWeek(String sdate, String num, String dateFormat) {
		Date dd = null;
		try {
			dd = convertStringToDate(dateFormat, sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (c.get(7) == 1) {
			c.add(5, -7);
		}

		if (num.equals("1")) {
			c.set(7, 2);
		} else if (num.equals("2")) {
			c.set(7, 3);
		} else if (num.equals("3")) {
			c.set(7, 4);
		} else if (num.equals("4")) {
			c.set(7, 5);
		} else if (num.equals("5")) {
			c.set(7, 6);
		} else if (num.equals("6")) {
			c.set(7, 7);
		} else if (num.equals("0")) {
			c.set(7, 1);
			c.add(5, 7);
		}
		return new SimpleDateFormat(dateFormat).format(c.getTime());
	}

	public static List getWeekList(Date endDate, int week) {
		List needList = new ArrayList();
		Calendar next = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String thisDate = sdf.format(new Date());
		String nextDate = sdf.format(next.getTime());

		while ((next.getTime().compareTo(endDate) < 0)
				|| (thisDate.equals(nextDate))) {
			int nextWeek = next.get(7);
			if (nextWeek == week) {
				needList.add(sdf.format(next.getTime()));
			}
			next.add(6, 1);
			nextDate = sdf.format(next.getTime());
		}
		return needList;
	}

	public static int getYear(Calendar c) {
		if (c != null) {
			return c.get(1);
		}

		return Calendar.getInstance().get(1);
	}

	public int getYearDays(int year) {
		int sum = 348;
		for (int i = 32768; i > 8; i >>= 1) {
			sum += ((lunarInfo[(year - 1900)] & i) > 0 ? 1 : 0);
		}
		return sum + getLeapDays(year);
	}

	public static List getYearList(Date endDate, int month, int dayth) {
		List needList = new ArrayList();
		Calendar next = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String thisDate = sdf.format(new Date());
		String nextDate = sdf.format(next.getTime());

		while ((next.getTime().compareTo(endDate) <= 0)
				|| (thisDate.equals(nextDate))) {
			int nextWeek = next.get(5);
			int nextMonth = next.get(2);
			if ((nextMonth == month) && (nextWeek == dayth)) {
				needList.add(sdf.format(next.getTime()));
			}
			next.add(6, 1);
			nextDate = sdf.format(next.getTime());
		}
		return needList;
	}

	public static long hoursToMinSec(int hours) {
		return minutesToMinSec(hours * 60);
	}

	public static long minutesToMinSec(int minutes) {
		return minutes * 60 * 1000;
	}
}
