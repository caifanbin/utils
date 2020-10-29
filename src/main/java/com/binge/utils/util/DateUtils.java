package com.binge.utils.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * 日期相关
 */
public class DateUtils {
	/**
	 * 
	 * @Title: monthStart   
	 * @Description: 获取当月起始日期  
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date monthStart() {
		Calendar cals = Calendar.getInstance();
		cals.set(cals.get(Calendar.YEAR), cals.get(Calendar.MONDAY), cals.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cals.set(Calendar.DAY_OF_MONTH, cals.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cals.getTime();
	}
	/**
	 * 
	 * @Title: monthEnd   
	 * @Description: 获取当月结束日期  
	 * @param: @return      
	 * @return: Date      
	 * @throws
	 */
	public static Date monthEnd() {
		Calendar cale = Calendar.getInstance();
		cale.set(cale.get(Calendar.YEAR), cale.get(Calendar.MONDAY), cale.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		cale.set(Calendar.HOUR_OF_DAY, 24);
		return cale.getTime();
	}

	/**
	 * 处理返回值
	 * Calendar格式转为 yyyy-MM-dd 字符串格式
	 */
	public static String returnDate(Calendar c) throws Exception {
		// 本月第一天的时间戳转换为字符串
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(sdf.format(new Date(c.getTimeInMillis())));
			//Date date = sdf.parse(sdf.format(new Long(s)));// 等价于
			return sdf.format(date);
		} catch (NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
		throw new Exception();
	}

	/**
	 * yyyy-MM-dd 格式日期转为 Calendar格式
	 */
	public static Calendar strDate2Cal(String yyyy2MM2dd) throws Exception{
		Date data = null;
		try {
			data = new SimpleDateFormat("yyyy-MM-dd").parse(yyyy2MM2dd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		// 设置时间，如果data为空，抛出异常
		c.setTime(Optional.ofNullable(data).orElseThrow());
		return c;
	}

	/**
	 * 获取指定日期所在月份开始的时间
	 * 传入日期格式：yyyy-MM-dd
	 * 返回格式：yyyy-MM-dd
	 */
	public static String getFirstDayDateOfMonth(String yyyy2MM2dd) throws Exception {

		final Calendar cal = strDate2Cal(yyyy2MM2dd);
		final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, last);
		return returnDate(cal);
	}

	/**
	 * 获取指定日期所在月份结束的时间
	 * 传入日期格式：yyyy-MM-dd
	 * 返回格式：yyyy-MM-dd
	 */
	public static String getLastDayOfMonth(String yyyy2MM2dd) throws Exception {

		final Calendar cal = strDate2Cal(yyyy2MM2dd);
		final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, last);
		return returnDate(cal);
	}

	/**
	 * 获取传入日期所在年的第一天
	 * 传入日期格式：yyyy-MM-dd
	 * 返回格式：yyyy-MM-dd
	 */
	public static String getFirstDayDateOfYear(String yyyy2MM2dd) throws Exception {

		final Calendar cal = strDate2Cal(yyyy2MM2dd);
		final int last = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, last);
		return returnDate(cal);
	}

	/**
	 * 获取传入日期所在年的最后一天
	 * 传入日期格式：yyyy-MM-dd
	 * 返回格式：yyyy-MM-dd
	 */
	public static String getLastDayOfYear(String yyyy2MM2dd) throws Exception {

		final Calendar cal = strDate2Cal(yyyy2MM2dd);
		final int last = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, last);
		return returnDate(cal);
	}

	/**
	 * 获取指定日期所在月份开始的时间
	 * 传入日期格式：yyyy-MM-dd
	 * 返回格式：yyyy-MM-dd
	 */
	public static String getMonthBegin(String yyyy2MM2dd) throws Exception {
		Calendar c = strDate2Cal(yyyy2MM2dd);

		//设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		//将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		//将分钟至0
		c.set(Calendar.MINUTE, 0);
		//将秒至0
		c.set(Calendar.SECOND,0);
		//将毫秒至0
		c.set(Calendar.MILLISECOND, 0);
		// 本月第一天的时间戳转换为字符串
		return returnDate(c);
	}


	/**
	 * 获取指定日期所在月份结束的时间
	 * 传入日期格式：yyyy-MM-dd
	 * 返回格式：yyyy-MM-dd
	 */
	public static String getMonthEnd(String yyyy2MM2dd) throws Exception {
		Calendar c = strDate2Cal(yyyy2MM2dd);

		//设置为当月最后一天
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		//将小时至23
		c.set(Calendar.HOUR_OF_DAY, 23);
		//将分钟至59
		c.set(Calendar.MINUTE, 59);
		//将秒至59
		c.set(Calendar.SECOND, 59);
		//将毫秒至999
		c.set(Calendar.MILLISECOND, 999);
		return returnDate(c);
	}
}
