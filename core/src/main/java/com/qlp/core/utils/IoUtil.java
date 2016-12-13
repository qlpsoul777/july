package com.qlp.core.utils;

import java.io.Closeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IoUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(IoUtil.class);

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
