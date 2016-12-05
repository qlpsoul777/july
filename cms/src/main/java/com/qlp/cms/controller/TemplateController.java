package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlp.cms.entity.Site;
import com.qlp.cms.service.CatalogService;
import com.qlp.cms.service.TemplateService;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.enums.ContentTypeEnum;
import com.qlp.core.enums.StatusEnum;
import com.qlp.core.utils.AssertUtil;

/**
 * 模板controller
 * @author qlp
 *
 */
@Controller
@RequestMapping("/template")
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private CatalogService catalogService;
	
	/**
	 * 模板管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request){
		return "/cms/template/manager";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public String tree(HttpServletRequest request){
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		String treeJson = catalogService.queryCatalogTree(site);
		return treeJson;
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request){
		
		return "/cms/template/list";
	}

}
