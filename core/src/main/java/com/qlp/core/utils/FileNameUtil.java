package com.qlp.core.utils;

import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;

public class FileNameUtil {
	
	private static final String ZIP_SUFFIX = ".zip";
	
	public static String getBeforeName(String originName){
		AssertUtil.assertNotBlank(originName, BusiErrorEnum.INPUT_NOT_EXIST, "文件名不能为null");
		
		int index0 = originName.lastIndexOf('/');
		int index = originName.lastIndexOf('.');
		if( index0 >= 0){
			if((index0 <index) && (index0 < originName.length())){
				originName = originName.substring(index0+1,index);
			}else{
				originName = originName.substring(index0+1,originName.length());
			}
		}else{
			if(index > 0){
				originName = originName.substring(0,index);
			}
		}
		
		return originName;
	}
	
	public static String getAfterName(String originName){
		AssertUtil.assertNotBlank(originName, BusiErrorEnum.INPUT_NOT_EXIST, "文件名不能为null");
		
		int index = originName.lastIndexOf('.');
		if( index > 0){
			originName = originName.substring(index,originName.length());
		}else{
			originName = "";
		}
		
		return originName;
	}
	
	public static String buildZipName(){
		return RandomUtil.getLongTimeRandom() + ZIP_SUFFIX;
	}


}
