package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlp.cms.dto.StaticFileDto;
import com.qlp.cms.util.StaticFileUtil;

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
}
