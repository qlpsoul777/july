package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qlp.cms.entity.Site;
import com.qlp.cms.service.CatalogService;
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.SysErrorEnum;
import com.qlp.core.utils.AssertUtil;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	@RequestMapping("/tree")
	public String tree(HttpServletRequest request){
		Site site = (Site) request.getSession().getAttribute(CmsConstant.SITE_KEY);
		AssertUtil.assertNotNull(site, SysErrorEnum.DOMAIN_NOT_FOUND, "无法从session中获取站点信息");
		String treeJson = catalogService.queryCatalogTree(site);
		System.out.println(treeJson);
		return treeJson;
	}

}
