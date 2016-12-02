package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.qlp.cms.entity.Catalog;
import com.qlp.cms.entity.Site;
import com.qlp.cms.service.CatalogService;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.enums.ContentTypeEnum;
import com.qlp.core.enums.StatusEnum;
import com.qlp.core.utils.AssertUtil;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	/**
	 * 栏目管理
	 * @param request
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request){
		request.setAttribute("statuss", StatusEnum.values());
		request.setAttribute("types", ContentTypeEnum.values());
		return "/cms/catalog/manager";
	}
	
	/**
	 * 栏目管理栏目树
	 * @param request
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public String tree(HttpServletRequest request){
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		String treeJson = catalogService.queryCatalogTree(site);
		return treeJson;
	}
	
	/**
	 * 删除栏目
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request,Long id){
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		String isSuccess = catalogService.delete(id);
		return isSuccess;
	}
	
	/**
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public String info(HttpServletRequest request,Long id){
		Catalog catalog = catalogService.get(id);
		return JSON.toJSONString(catalog);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,HttpServletResponse response,@ModelAttribute Catalog catalog,Long pId) throws Exception{
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		catalog.setSite(site);
		
		Catalog parent = null;
		if(pId != null){
			parent = catalogService.get(pId);
			catalog.setParent(parent);
		}
		String path = catalogService.getPath(site,parent,catalog.getAlias());
		catalog.setPath(path);
		catalogService.save(catalog);
		return "1";
	}
	
	
	
	

}
