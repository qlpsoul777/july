package com.qlp.cms.controller;

import com.alibaba.fastjson.JSON;
import com.qlp.cms.entity.Catalog;
import com.qlp.cms.entity.Site;
import com.qlp.cms.service.CatalogService;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.enums.ContentTypeEnum;
import com.qlp.core.enums.StatusEnum;
import com.qlp.core.utils.AssertUtil;
import com.qlp.core.utils.ConstantsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

		return catalogService.queryCatalogTree(site);
	}
	
	/**
	 * 删除栏目
	 * @param id
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long id){
		return catalogService.delete(id);
	}
	
	/**
	 * 查看栏目信息
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	@ResponseBody
	public String info(Long id){
		Catalog catalog = catalogService.get(id);
		return JSON.toJSONString(catalog);
	}

	/**
	 * 保存栏目信息
	 * @param request
	 * @param catalog
	 * @param pId
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(HttpServletRequest request,@ModelAttribute Catalog catalog,Long pId){
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
		return ConstantsUtil.SUCCESS;
	}

}
