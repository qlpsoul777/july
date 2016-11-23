package com.qlp.core.utils;

import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 * ---继承自apache.commons.lang3.StringUtils
 * @author qlp
 *
 */
public class StringUtil extends StringUtils{

	/**
	 * 首字母大写
	 * 	如果参数为空或者是空串直接返回<code>null</code>
	 * 	如果首字母是小写，就进行转换，否则原样输出。
	 * @param in 
	 * @return
	 */
	public static String firstCharUpper(String in){
		if(null == in || in.trim().length() == 0){
			return null;
		}
		char c = in.charAt(0);
		if(c >= 'a' || c <= 'z'){
			StringBuilder sb = new StringBuilder(in.length());
			sb.append(Character.toUpperCase(c)).append(in.substring(1));
			in = sb.toString();
		}
		return in;
	}

	public static Long[] toLongArray(String input, String septate) {
		StringTokenizer st = new StringTokenizer(input, septate);
		Long[] result = new Long[st.countTokens()];
		int count = 0;
		while(st.hasMoreElements()){
			result[count++] = Long.parseLong((String) st.nextElement());
		}
		return result;
	}

}
