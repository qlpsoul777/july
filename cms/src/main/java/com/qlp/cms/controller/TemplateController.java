package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qlp.cms.entity.Catalog;
import com.qlp.cms.entity.Site;
import com.qlp.cms.entity.Template;
import com.qlp.cms.service.CatalogService;
import com.qlp.cms.service.TemplateService;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.enums.StatusEnum;
import com.qlp.core.utils.AssertUtil;
import com.qlp.core.utils.DataConvertUtil;
import com.qlp.enums.TemplateTypeEnum;

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
	public String manager(){
		return "/cms/template/manager";
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public String tree(HttpServletRequest request){
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		return catalogService.queryCatalogTree(site);
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,@ModelAttribute Template template,Long catalogId){
		Catalog catalog;
		if(catalogId != null){
			catalog = catalogService.get(catalogId);
			AssertUtil.assertNotNull(catalog,BusiErrorEnum.OUTPUT_NOT_FOUND,"根据传入参数catalogId未查询到栏目信息");
			request.getSession().setAttribute(CmsConstant.CATALOG_KEY, catalog);
		}else{
			catalog = (Catalog) request.getSession().getAttribute(CmsConstant.CATALOG_KEY);
		}
		template.setCatalog(catalog);
		
		int pageNum = DataConvertUtil.toInt(request.getParameter("currentPage"), 0);
		int pageSize = DataConvertUtil.toInt(request.getParameter("pageSize"), 10);
		Pageable pageable = new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.DESC,"status","createTime"));
		
		Page<Template> pageInfo = templateService.queryPageBySite(template,pageable);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("catalogId", catalogId);
		return "/cms/template/list";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,Long id){
		Template template = templateService.newIfNotFound(id);
		request.setAttribute("template", template);
		request.setAttribute("statuss", StatusEnum.values());
		request.setAttribute("types", TemplateTypeEnum.values());
		return "/cms/template/edit";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,@ModelAttribute Template template){
		Catalog catalog = (Catalog) request.getSession().getAttribute(CmsConstant.CATALOG_KEY);
		AssertUtil.assertNotNull(catalog, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取栏目信息");
		template.setCatalog(catalog);
		
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		template.setSite(site);
		
		templateService.save(template);
		return "redirect:list";
	}

}
