package com.qlp.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
		List<Site> sites = siteService.findAll();
		request.setAttribute("sites", sites);
		return "/cms/site/list";
	}

}
