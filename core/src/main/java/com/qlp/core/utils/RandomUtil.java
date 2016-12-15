package com.qlp.core.utils;

import java.util.Date;
import java.util.UUID;

/**
 * Created by july on 2016/12/15.
 */
public class RandomUtil {

    /**
     * 获取uuid随机数
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String getLongTimeRandom(int len){
        String result = DateUtil.date2String(new Date(), DateUtil.FormatDate.YMDHMSS);

        result += (int)(Math.random()* Math.pow(10, len));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getLongTimeRandom(6));
    }

}
