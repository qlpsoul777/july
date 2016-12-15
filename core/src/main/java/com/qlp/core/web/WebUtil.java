package com.qlp.core.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.qlp.core.utils.IoUtil;
import com.qlp.core.utils.LogUtil;
import com.qlp.core.utils.StringUtil;

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
	
	public static void setDownloadResponseHeader(HttpServletRequest request,
			HttpServletResponse response, File file) {
		response.setContentType("application/octet-stream; charset=UTF-8");
		
		String fileName = StringUtil.trim(file.getName());
		LogUtil.info(logger, "原始文件名：{0}", fileName);
		try {
			fileName = encodeFileName(request, response,fileName);
		} catch (UnsupportedEncodingException e) {
			LogUtil.error(logger, "文件名{0}编码时出错：{1}",fileName, e);
		}
		LogUtil.info(logger, "编码后文件名：{0}", fileName);
		response.addHeader("Content-Disposition", "attachment; " + fileName);
		
		response.addHeader("Content-Length", "" + file.length());
		
	}

	private static String encodeFileName(HttpServletRequest request,
			HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
		String userAgent = request.getHeader("User-Agent").toLowerCase();
	    if (StringUtil.isEmpty(userAgent)){
	    	return "filename=" + URLEncoder.encode(fileName, "UTF-8");
	    }
	    if (StringUtil.containsIgnoreCase(userAgent, "msie")){
	    	return "filename=" + URLEncoder.encode(fileName, "UTF-8");
	    }
	    if (StringUtil.contains(userAgent, "trident")){
	    	return "filename=" + URLEncoder.encode(fileName, "UTF-8");
	    }
	    if (StringUtil.containsIgnoreCase(userAgent, "opera")) {
	      return "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8");
	    }
	    return "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
	}

	public static void responseJson(HttpServletResponse response, Object obj) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSONObject.toJSONString(obj));
			writer.flush();
		} catch (IOException e) {
			LogUtil.error(logger, "从HttpServletResponse获取PrintWriter时出错：{0}", e);
		}finally{
			IoUtil.close(writer);
		}
	}

}
