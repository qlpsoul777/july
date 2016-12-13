package com.qlp.core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;

public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	private static final String[] texts = {".css",".html",".js",".txt",".json",".xml"};
	
	private static final String[] photos = {".jpg",".png",".jpeg",".gif"};
	
	
	public static boolean isNormalText(File file){
		
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		for(String name : texts){
			if(StringUtil.equalsIgnoreCase(name,getAfterName(file.getName()))){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isPhoto(File file){
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		for(String name : photos){
			if(StringUtil.equalsIgnoreCase(name,getAfterName(file.getName()))){
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

	public static void writeOutFile(OutputStream os, File file) {
		InputStream fis = null;
		int count = 0;
		byte[] buffer = new byte[1024 * 1024];
		try {
			fis = new BufferedInputStream(new FileInputStream(file));
			while ((count = fis.read(buffer)) != -1){
				os.write(buffer, 0, count);
			}
			os.flush();
		} catch (Exception e) {
			LogUtil.error(logger, "写出文件{0}出错：{1}", file.getAbsolutePath(),e);
		}finally{
			IoUtil.close(fis,os);
		}
		
	}
	
	public static void compress(String filePath){
		
	}
	
	public static void compress(File file){
		
	}
	
	/*BufferedReader bufReader = null;
	StringBuilder sb = new StringBuilder();
	String temp;
	try {
		bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
		while((temp = bufReader.readLine()) != null){
			sb.append(temp);
		}
		response.getWriter().write(sb.toString());
	} catch (Exception e) {
		LogUtil.error(logger, "文件不存在：{0}", e);
	} finally{
		if(bufReader != null){
			try {
				bufReader.close();
			} catch (IOException e) {
				LogUtil.error(logger, "关闭BufferedReader出错：{0}", e);
			}
		}
	}*/
	
}
