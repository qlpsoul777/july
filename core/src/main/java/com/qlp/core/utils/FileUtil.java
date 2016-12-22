package com.qlp.core.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.Exception.MyException;

/**
 * 文件操作工具类
 */
public class FileUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

	private static final boolean DEFAULT_ISDELETE = Boolean.FALSE;
	
	/**
	 * 输入流写入文件
	 * @param ins
	 * @param file
	 */
	public static void writeInFile(InputStream ins, File file) {
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		//AssertUtil.assertTrue(file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不能是文件夹");
		
		OutputStream os = null;
		int count;
		byte[] buffer = new byte[1024];
		try {
			os = new BufferedOutputStream(new FileOutputStream(file));
			while ((count = ins.read(buffer)) != -1) {
				os.write(buffer, 0, count);
			}
			os.flush();
		} catch (Exception e) {
			LogUtil.error(logger, "写入文件{0}出错：{1}", file.getAbsolutePath(),e);
		}finally{
			IoUtil.close(ins,os);
		}
	}

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
		
		AssertUtil.assertNotNull(file, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(file.exists() && file.isFile(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在/文件不能是文件夹");
		
		InputStream fis = null;
		int count;
		byte[] buffer = new byte[1024];
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
	 * @param file 文件路径
	 */
	public static void deleteAllFiles(File file) {
		if (!file.exists()){
			return;
		}
		
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
	 * 压缩文件或文件夹
	 * @param sourcePath 待压缩文件全路径
	 * @param targetPath 输出压缩文件全路径
	 * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
	 */
	public static void compress(String sourcePath,String targetPath,boolean dirFlag){
		
		AssertUtil.assertNotNull(sourcePath, BusiErrorEnum.INPUT_NOT_EXIST, "源文件路径不能为null");
		AssertUtil.assertNotNull(targetPath, BusiErrorEnum.INPUT_NOT_EXIST, "目标文件路径不能为null");
		
		compress(new File(sourcePath),new File(targetPath),dirFlag);
	}

	/**
	 * 压缩文件或文件夹
	 * @param sourceFile 待压缩文件
	 * @param targetFile 输出压缩文件
	 * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
	 */
	public static void compress(File sourceFile,File targetFile,boolean dirFlag){
		
		AssertUtil.assertNotNull(sourceFile, BusiErrorEnum.INPUT_NOT_EXIST, "文件不能为null");
		AssertUtil.assertTrue(sourceFile.exists(),BusiErrorEnum.INPUT_STATE_ILLEGAL, "文件不存在");
		
		//判断压缩文件保存的路径是否为源文件路径的子文件夹，如果是，则抛出异常（防止无限递归压缩的发生）
        if (sourceFile.isDirectory() && (StringUtil.indexOf(targetFile.getPath(), sourceFile.getPath()) != -1)) {
        	throw new MyException("压缩文件保存的路径不能是源文件路径的子路径");
        }
        
        //判断压缩文件保存的路径是否存在，如果不存在，则创建
        createParentIfNotExists(targetFile);
        
        ZipOutputStream os = null;
        try {
			os = new ZipOutputStream(new CheckedOutputStream(new FileOutputStream(targetFile), new CRC32()));
			zip(os,sourceFile,dirFlag);
		} catch (FileNotFoundException e) {
			
		}finally{
			IoUtil.close(os);
		}
        
	}
	
	/**
	 * 解压zip文件
	 * @param file
	 * @param parent
	 * @param b
	 */
	public static void deCompress(File file, File parent, boolean b) {
		
		
	}
	
	/**
     * 解压缩zip包
     * @param zipFilePath zip文件的全路径
     * @param unzipFilePath 解压后的文件保存的路径
     * @param includeZipFileName 解压后的文件保存的路径是否包含压缩文件的文件名。true-包含；false-不包含
     */
    @SuppressWarnings("unchecked")
    public static void unzip(String zipFilePath, String unzipFilePath, boolean includeZipFileName) throws Exception {
        if (StringUtil.isEmpty(zipFilePath) || StringUtil.isEmpty(unzipFilePath)) {

        }
        File zipFile = new File(zipFilePath);
        //如果解压后的文件保存路径包含压缩文件的文件名，则追加该文件名到解压路径
        if (includeZipFileName) {
            String fileName = zipFile.getName();
            if (StringUtil.isNotEmpty(fileName)) {
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
            }
            unzipFilePath = unzipFilePath + File.separator + fileName;
        }
        //创建解压缩文件保存的路径
        File unzipFileDir = new File(unzipFilePath);
        if (!unzipFileDir.exists() || !unzipFileDir.isDirectory()) {
            unzipFileDir.mkdirs();
        }

        //开始解压
        ZipEntry entry = null;
        String entryFilePath = null, entryDirPath = null;
        File entryFile = null, entryDir = null;
        int index = 0, count = 0, bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        ZipFile zip = new ZipFile(zipFile);
        Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();
        //循环对压缩包里的每一个文件进行解压     
        while (entries.hasMoreElements()) {
            entry = entries.nextElement();
            //构建压缩包中一个文件解压后保存的文件全路径
            entryFilePath = unzipFilePath + File.separator + entry.getName();
            //构建解压后保存的文件夹路径
            index = entryFilePath.lastIndexOf(File.separator);
            if (index != -1) {
                entryDirPath = entryFilePath.substring(0, index);
            } else {
                entryDirPath = "";
            }
            entryDir = new File(entryDirPath);
            //如果文件夹路径不存在，则创建文件夹
            if (!entryDir.exists() || !entryDir.isDirectory()) {
                entryDir.mkdirs();
            }

            //创建解压文件
            entryFile = new File(entryFilePath);
            if (entryFile.exists()) {
                //检测文件是否允许删除，如果不允许删除，将会抛出SecurityException
                SecurityManager securityManager = new SecurityManager();
                securityManager.checkDelete(entryFilePath);
                //删除已存在的目标文件
                entryFile.delete();
            }

            //写入文件
            bos = new BufferedOutputStream(new FileOutputStream(entryFile));
            bis = new BufferedInputStream(zip.getInputStream(entry));
            while ((count = bis.read(buffer, 0, bufferSize)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();
            bos.close();
        }
    }


	private static void zip(ZipOutputStream os, File sourceFile, boolean dirFlag) {
		if(sourceFile.isDirectory()){
			File[] files = sourceFile.listFiles();
			for(File file:files){
				if (dirFlag) {
					recursionZip(os, file, sourceFile.getName() + File.separator);
	            } else {
	                recursionZip(os, file, "");
	            }
			}
		}else{
			recursionZip(os, sourceFile, "");
		}
	}

	private static void recursionZip(ZipOutputStream zos, File file,
			String baseDir) {
		if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileSec : files) {
                recursionZip(zos, fileSec, baseDir + file.getName() + File.separator);
            }
        } else {
            byte[] buf = new byte[1024];
            int count;
            InputStream fis = null;
            try {
				fis = new BufferedInputStream(new FileInputStream(file));
				zos.putNextEntry(new ZipEntry(baseDir + file.getName()));
				while ((count = fis.read(buf)) != -1){
					zos.write(buf, 0, count);
				}
				zos.flush();
			} catch (Exception e) {
				
			}finally{
				IoUtil.close(fis);
			}
        }
		
	}

	private static File createParentIfNotExists(File targetFile) {
		if (!targetFile.exists()) {
			if(StringUtil.indexOf(targetFile.getName(), ".") != -1){
				File parent = targetFile.getParentFile();
				if(!parent.exists()){
					parent.mkdirs();
				}
				return new File(parent,targetFile.getName());
			}
        }
		return targetFile;
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
