package com.qlp.core.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlp.core.utils.LogUtil;

public class WebUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(WebUtil.class);
	
	public static OutputStream getFromResponse(HttpServletResponse response){
		OutputStream os = null;
		try {
			os = response.getOutputStream();
		} catch (IOException e) {
			LogUtil.error(logger, "从HttpServletResponse获取输出流时出错：{0}", e);
		}
		return os;	
	}

}
