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

import com.qlp.cms.entity.Site;
import com.qlp.cms.service.SiteService;
import com.qlp.core.Exception.ErrorDetail;
import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.enums.StatusEnum;
import com.qlp.core.utils.AssertUtil;
/**
 * 站点管理controller
 * @author july
 *
 */
@Controller
@RequestMapping(value = "/site")
public class SiteController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,@ModelAttribute Site site){
		int pageNum = Integer.parseInt(request.getParameter("currentPage")== null?"0":request.getParameter("currentPage"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize")== null?"10":request.getParameter("pageSize"));
		
		Pageable pageable = new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.ASC, "createTime"));
        Page<Site> pageInfo = siteService.queryPageBySite(site,pageable);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("site", site);
		return "/cms/site/list";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request){
		String id = request.getParameter("id");
		Site site = siteService.query(id);
		request.setAttribute("site", site);
		request.setAttribute("statuss", StatusEnum.values());
		return "/cms/site/edit";
	}
	
	@RequestMapping("/save")
	public String save(HttpServletRequest request,@ModelAttribute Site site){
		siteService.save(site);
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request){
		String ids =  request.getParameter("ids");
		siteService.deleteByIds(ids);
		return "redirect:list";
	}
	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request){
		String id = request.getParameter("id");
		Site site = siteService.query(id);
		AssertUtil.assertNotNull(site, BusiErrorEnum.OUTPUT_NOT_FOUND, "未查询到站点信息");
		request.getSession().setAttribute("SITEINFO", site);
		return "/cms/catalog/manager";
	}

}
