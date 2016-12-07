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
		YMD_1("yyyy-MM-dd");
		
		private final String pattern; 
		
		private FormatDate(String pattern){
			this.pattern = pattern;
		}

		public DateFormat getFormat(){
			SimpleDateFormat sdf = new SimpleDateFormat(this.pattern);
			return sdf;
		}
	}
	
	/**
	 * 日期时间转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date,FormatDate format){
		String str = format.getFormat().format(date);
		return str;
	}
	
	/**
	 * 字符串转日期时间
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date string2Date(String dateStr,FormatDate format){
		try {
			Date date = format.getFormat().parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String stamp2DateStr(long times,FormatDate format){
		Date date =new Date(times);
		return date2String(date,format);
	}

}
