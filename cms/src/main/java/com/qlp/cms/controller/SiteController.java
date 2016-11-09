package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qlp.cms.entity.Site;
import com.qlp.cms.service.SiteService;

@Controller
@RequestMapping(value = "/site")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		Site site = new Site();
		site.setNum("org");
		Pageable pageable = new PageRequest(0, 2);
		Page<Site> pageInfo = siteService.findPage(pageable,site);
		request.setAttribute("pageInfo", pageInfo);
		return "/cms/site/list";
	}
	
	

}
