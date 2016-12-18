package com.qlp.core.utils;

import java.util.Date;
import java.util.UUID;

/**
 * 随机数生成工具类
 */
public class RandomUtil {
	private static final int DEFAULT_LEN = 6;
	private static final int MAX_LEN = 9;

    /**
     * 获取uuid随机数
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String getLongTimeRandom(){
        String result = DateUtil.date2String(new Date(), DateUtil.FormatDate.YMDHMSS);
        result += getFixLenRandomNumber(DEFAULT_LEN);
        return result;
    }
    
    public static String getFixLenRandomNumber(int len){
    	if(len > MAX_LEN){
    		len = MAX_LEN;
    	}
    	return String.valueOf((int)(Math.random()* Math.pow(10, len)));
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }

}
