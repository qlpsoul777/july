package com.qlp.core.utils;

import java.text.DecimalFormat;

/**
 * 单位转换工具类
 * @author qlp
 *
 */
public class UnitConverterUtil {
	
	private static final String[] FILE_SIZE_UNITS = {"KB","MB","GB","TB","PB"};
	/**
	 * 返回带单位的文件大小
	 * @param size 必须是KB为单位的Long值
	 * @return
	 */
	public static String getFileSize(Long val){
		int num = 0;
		double size = Double.parseDouble(val/1024L +"");
		while(size >= 1024D && num < FILE_SIZE_UNITS.length-1){
			size = size/1024D;
			num++;
		}
		DecimalFormat df = new DecimalFormat("#.0");
		return df.format(size) + FILE_SIZE_UNITS[num];
	}
	
	public static void main(String[] args) {
		System.out.println(getFileSize(10L));
	}

}
