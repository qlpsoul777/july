package com.qlp.cms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qlp.cms.dao.SiteDao;
import com.qlp.cms.entity.Site;

@Service("siteService")
@Transactional(readOnly = true)
public class SiteService {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteService.class);

	@Autowired
	private SiteDao siteDao;
	
	public List<Site> findAll(){
		return siteDao.findAll();
	}
}
