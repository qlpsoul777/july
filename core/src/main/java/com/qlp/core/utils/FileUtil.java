package com.qlp.core.utils;

import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件操作工具类
 */
public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	private static final String[] TEXTS = {".css",".html",".js",".txt",".json",".xml"};
	
	private static final String[] PHOTOS = {".jpg",".png",".jpeg",".gif"};

	private static final String ZIP = ".zip";

	private static final String TEMP_DIR = "temp";

	/**
	 * 判断文件是否是普通文本文件
	 * @param file
	 * @return
	 */
	public static boolean isNormalText(File file){
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		for(String name : TEXTS){
			if(StringUtil.equalsIgnoreCase(name,getAfterName(file.getName()))){
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断文件是否是图片文件
	 * @param file
	 * @return
	 */
	public static boolean isPhoto(File file){
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		for(String name : PHOTOS){
			if(StringUtil.equalsIgnoreCase(name,getAfterName(file.getName()))){
				return true;
			}
		}
		return false;
	}

	/**
	 *	将文件用指定输出流输出
	 * @param os
	 * @param file
	 */
	public static void writeOutFile(OutputStream os, File file) {
		InputStream fis = null;
		int count;
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

	/**
	 * 删除指定文件
	 * @param path
	 */
	public static void deleteAllFiles(File path) {
		if (!path.exists())
			return;
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFiles(files[i]);
		}
		path.delete();
	}

	/**
	 * 生成临时目录下zip文件
	 * @param parentPath
	 * @return
	 */
	public static File createZipFile(String parentPath) {
		File downLoadFile = new File(parentPath,TEMP_DIR);
		if(!downLoadFile.exists()){
			downLoadFile.mkdirs();
		}
		downLoadFile = new File(downLoadFile,RandomUtil.getLongTimeRandom(6)+ZIP);
		return downLoadFile;
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


	
	public static void compress(String source, String target){
		int readedBytes;
		byte[] buffer = new byte[4096];
		try
		{
			File srcFile = new File(source);
			if (srcFile.isFile())
			{
				ZipFile zipFile = new ZipFile(source);

				for (Enumeration entries = zipFile.entries(); entries.hasMoreElements(); )
				{
					ZipEntry entry = (ZipEntry)entries.nextElement();
					File file = new File(target + "/" + entry.getName());

					if (entry.isDirectory())
					{
						file.mkdirs();
					}
					else
					{
						File parent = file.getParentFile();
						if (!parent.exists())
						{
							parent.mkdirs();
						}

						InputStream inputStream = zipFile.getInputStream(entry);
						FileOutputStream outStream = new FileOutputStream(file);
						while ((readedBytes = inputStream.read(buffer)) > 0)
						{
							outStream.write(buffer, 0, readedBytes);
						}
						outStream.close();
						inputStream.close();
					}
				}
				zipFile.close();
			}
		}
		catch (IOException ex)
		{
			logger.error("", ex);
		}
		
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
