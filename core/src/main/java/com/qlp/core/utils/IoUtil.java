package com.qlp.core.utils;

import java.io.Closeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IO工具类
 * @author qlp
 *
 */
public class IoUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(IoUtil.class);

	/**
	 * 关闭多个Closeable
	 * @param closeables
	 */
	public static void close(Closeable ... closeables){
		if (closeables != null) {
			try {
				for (Closeable closeable : closeables) {
	                if (closeable != null) {
	                    closeable.close();
	                }
	            }
			} catch (Exception e) {
				LogUtil.error(logger, "关闭流出错：{1}",e);
			}
        }
	}
	
	

}
