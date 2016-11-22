package com.qlp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;

public final class DataConvertUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DataConvertUtil.class);
	
	public static String toStr(Object obj){
		AssertUtil.assertNotNull(obj, BusiErrorEnum.INPUT_NOT_EXIST);
		return String.valueOf(obj);
	}
	
	public static Long toLong(Object obj){
		return toLong(obj,0L);
	}
	
	public static Long toLong(Object obj,Long defaultVal){
		try {
			defaultVal = Long.parseLong(toStr(obj));
		} catch (Exception e) {
			LogUtil.error(logger, "{0}转Long出错：{1}",obj,e.getMessage());
		}
		return defaultVal;
	}

}