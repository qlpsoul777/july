package com.qlp.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 * @author qlp
 *
 */
public class DateUtil {
	
	public enum FormatDate{
		YMD_1("yyyy-MM-dd"),
		YMDHMSS("yyyyMMddHHmmssSSS");
		
		private final String pattern; 
		
		private FormatDate(String pattern){
			this.pattern = pattern;
		}

		public DateFormat getFormat(){
			return new SimpleDateFormat(this.pattern);
		}
	}
	
	/**
	 * 日期时间转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date,FormatDate format){
		return format.getFormat().format(date);
	}
	
	/**
	 * 字符串转日期时间
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date string2Date(String dateStr,FormatDate format){
		try {
			return format.getFormat().parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间戳值转字符串
	 * @param times
	 * @param format
	 * @return
	 */
	public static String stamp2DateStr(long times,FormatDate format){
		Date date =new Date(times);
		return date2String(date,format);
	}

}
