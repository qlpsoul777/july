package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlp.cms.entity.Site;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.utils.AssertUtil;

@Controller
@RequestMapping("/staticFile")
public class StaticFileController {
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		
		
		return "/cms/staticFile/list";
	}
}
