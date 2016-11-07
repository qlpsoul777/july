package com.qlp.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/site")
public class SiteController {
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		return "/cms/site/list";
	}

}
