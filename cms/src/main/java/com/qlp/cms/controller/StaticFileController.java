package com.qlp.cms.controller;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlp.cache.GlobalCache;
import com.qlp.cms.dto.StaticFileDto;
import com.qlp.cms.util.StaticFileUtil;
import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.utils.AssertUtil;
import com.qlp.core.utils.FileUtil;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder.Output;

/**
 * 文件管理controller
 * @author qlp
 *
 */
@Controller
@RequestMapping("/staticFile")
public class StaticFileController {
	
	/**
	 * 文件列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		
		String filePath = StaticFileUtil.getFilePath(request);
		request.setAttribute("filePath", filePath);
		
		Page<StaticFileDto> pageInfo = StaticFileUtil.getPageInfo(request,filePath);
		request.setAttribute("pageInfo", pageInfo);
		return "/cms/staticFile/list";
	}
	
	@RequestMapping("/preView")
	public void preView(HttpServletRequest request,HttpServletResponse response,String path){
		AssertUtil.assertNotBlank(path, BusiErrorEnum.INPUT_NOT_EXIST, "文件路径不能为空");
		
		File file = new File(GlobalCache.dataPath, path);
		if(file.exists() && file.isFile()){
			if(FileUtil.isNormalText(file)){
				response.setContentType("text/plain;charset=utf-8");
				InputStreamReader  isr = null;
				StringBuilder sb = new StringBuilder();
				String temp;
				try {
					isr = new InputStreamReader(new FileInputStream(file),"utf-8");
					BufferedReader bufReader = new BufferedReader(isr);
					while((temp = bufReader.readLine()) != null){
						sb.append(temp);
					}
					response.getWriter().write(sb.toString());
				} catch (FileNotFoundException e) {
					
				} catch (IOException e) {
					
				}finally{
					if(isr != null){
						try {
							isr.close();
						} catch (IOException e) {

						}
					}
				}
			}
		}
	}
}
