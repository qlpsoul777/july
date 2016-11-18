package com.qlp.cms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
	public String list(HttpServletRequest request){
		int pageNum = Integer.parseInt(request.getParameter("currentPage")== null?"0":request.getParameter("currentPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize")== null?"10":request.getParameter("pageSize"));
		
		Pageable pageable = new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.ASC, "createTime"));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a_name_li", "阿");
        Page<Site> pageInfo = siteService.queryPageByMap(map,pageable);
		request.setAttribute("pageInfo", pageInfo);
		return "/cms/site/list";
	}
	
	

}
