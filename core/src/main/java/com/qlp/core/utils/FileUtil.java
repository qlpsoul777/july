package com.qlp.core.utils;

import java.io.File;

import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;

public class FileUtil {
	
	private static final String[] texts = {".css",".html",".js",".txt",".json",".xml"};
	
	private static final String[] photos = {".jpg",".png",".jpeg",".gif"};
	
	
	public static boolean isNormalText(File file){
		
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		for(String name : texts){
			if(StringUtil.equalsIgnoreCase(name,getAfterName(file.getName()) ));{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isPhoto(File file){
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		for(String name : photos){
			if(StringUtil.equalsIgnoreCase(name,getAfterName(file.getName()) ));{
				return true;
			}
		}
		
		return false;
		
	}
	
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
	
}
