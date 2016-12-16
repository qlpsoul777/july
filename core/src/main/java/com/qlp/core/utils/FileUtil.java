package com.qlp.core.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 */
public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	

	private static final String TEMP_DIR = "temp";
	
	private static final boolean DEFAULT_ISDELETE = Boolean.FALSE;

	/**
	 *	将文件用指定输出流输出
	 * @param os 输出流
	 * @param file 文件,不能是文件夹或null
	 */
	public static void writeOutFile(OutputStream fromResponse, File file) {
		writeOutFile(fromResponse,file,DEFAULT_ISDELETE);
	}

	/**
	 *	将文件用指定输出流输出，并根据isDelete决定输出后是否删除原文件true：删除；false：不删除
	 * @param os 输出流
	 * @param file 文件,不能是文件夹或null
	 * @param isDelete 输出后是否删除原文件 
	 */
	public static void writeOutFile(OutputStream os, File file,boolean isDelete) {
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
			if(isDelete){
				file.delete();
			}
			IoUtil.close(fis,os);
		}

	}

	/**
	 * 删除指定文件或文件夹
	 * @param path
	 */
	public static void deleteAllFiles(File file) {
		if (!file.exists())
			return;
		if (file.isFile()) {
			file.delete();
			return;
		}
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			deleteAllFiles(files[i]);
		}
	}

	/**
	 * 在临时目录下生成zip文件
	 * @param parentPath
	 * @return
	 */
	public static File createZipFile(String parentPath) {
		File file = new File(parentPath,TEMP_DIR);
		if(!file.exists()){
			file.mkdirs();
		}
		file = new File(file,FileNameUtil.buildZipName());
		return file;
	}

	

	
	public static void compress(String sourcePath, String targetPath,String fileName){
		
	}
	
	public static void compress(File sourceFile,File targetFile){
		
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
