package com.qlp.cms.service;

import java.util.Arrays;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlp.cms.dao.SiteDao;
import com.qlp.cms.entity.Site;
import com.qlp.core.utils.LogUtil;
import com.qlp.core.utils.StringUtil;

@Service("siteService")
@Transactional(readOnly = true)
public class SiteService {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteService.class);

	@Autowired
	private SiteDao siteDao;
	
	public Site query(Long id){
		return siteDao.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public Site save(Site site){
		return siteDao.saveAndFlush(site);
	}
	
	@Transactional(readOnly = false)
	public void deleteByIds(String ids){
		LogUtil.info(logger, "待删除ids:{0}", ids);
		if(StringUtil.isNotBlank(ids)){
			Long[] id = StringUtil.toLongArray(ids,",");
			LogUtil.info(logger, "删除:{0}条数据", id.length);
			siteDao.deleteByIdIn(id);
		}
	}
	
	public Page<Site> queryPageByMap(Map<String,Object> map, Pageable pageable) {
		return siteDao.queryPageByMap(map, pageable);
	}
	
	public Page<Site> queryPageBySite(Site site,Pageable pageable){
		ExampleMatcher matcher = ExampleMatcher.matching()     
				  .withIgnorePaths("createTime")
		.withIgnoreNullValues().withMatcher("name",GenericPropertyMatchers.contains()).withMatcher("status", GenericPropertyMatchers.exact());
	
		Example<Site> example = Example.of(site, matcher);
		return siteDao.findAll(example, pageable);
	}

}
