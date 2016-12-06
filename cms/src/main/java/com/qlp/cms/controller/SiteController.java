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
import com.qlp.constant.CmsConstant;
import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.enums.StatusEnum;
import com.qlp.core.utils.AssertUtil;
import com.qlp.core.utils.DataConvertUtil;
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
	
	/**
	 * 站点列表
	 * @param request
	 * @param site
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request,@ModelAttribute Site site){
		int pageNum = DataConvertUtil.toInt(request.getParameter("currentPage"), 0);
		int pageSize = DataConvertUtil.toInt(request.getParameter("pageSize"), 10);
		
		Pageable pageable = new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.DESC,"status","createTime"));
        Page<Site> pageInfo = siteService.queryPageBySite(site,pageable);
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("site", site);
		return "/cms/site/list";
	}
	
	/**
	 * 站点编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request,Long id){
		Site site = siteService.newIfNotFound(id);
		request.setAttribute("site", site);
		request.setAttribute("statuss", StatusEnum.values());
		return "/cms/site/edit";
	}
	
	/**
	 * 保存站点信息
	 * @param request
	 * @param site
	 * @return
	 */
	@RequestMapping("/save")
	public String save(HttpServletRequest request,@ModelAttribute Site site){
		siteService.save(site);
		return "redirect:list";
	}
	
	/**
	 * 批量删除站点信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request){
		String ids =  request.getParameter("ids");
		siteService.deleteByIds(ids);
		return "redirect:list";
	}
	
	/**
	 * 站点管理
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request,Long id){
		Site site = siteService.query(id);
		AssertUtil.assertNotNull(site, BusiErrorEnum.OUTPUT_NOT_FOUND, "未查询到站点信息");
		request.getSession().setAttribute(CmsConstant.SITE_KEY, site);
		return "/cms/site/manager";
	}

}
