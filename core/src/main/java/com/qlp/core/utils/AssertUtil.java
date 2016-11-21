package com.qlp.core.utils;

import java.util.Map;

import com.qlp.core.Exception.ErrorDetail;
import com.qlp.core.Exception.MyException;

public final class AssertUtil {
	
	/**
	 * 判断对象不为null,否则抛出MyException
	 * @param o
	 * @param detail
	 * @param msg
	 */
	public static void assertNotNull(Object o,ErrorDetail detail,String msg){
		if(null == o){
			throw new MyException(detail, msg);
		}
	}
	
	/**
	 * 判断字符串不为blank,否则抛出MyException
	 * @param str
	 * @param detail
	 * @param msg
	 */
	public static void assertNotBlank(String str,ErrorDetail detail,String msg){
		if(StringUtil.isBlank(str)){
			throw new MyException(detail, msg);
		}
	}
	
	public static void assertNotBlank(Map<?,?> map,ErrorDetail detail,String msg){
		if(CollectionUtil.isBlank(map)){
			throw new MyException(detail, msg);
		}
	}
	
	public static void assertTrue(boolean result,ErrorDetail detail,String msg){
		if(result){
			throw new MyException(detail, msg);
		}
	}

}
