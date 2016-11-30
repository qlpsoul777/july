package com.qlp.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.qlp.cms.dao.CatalogDao;
import com.qlp.cms.entity.Catalog;
import com.qlp.cms.entity.Site;
import com.qlp.core.utils.CollectionUtil;

@Service("catalogService")
@Transactional(readOnly = true)
public class CatalogService {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);
	
	@Autowired
	private CatalogDao catalogDao;

	public String queryCatalogTree(Site site) {
		List<Catalog> oneLevels = catalogDao.findCatalogTree(site.getId());
		Catalog root = new Catalog(0L,site.getName());
		root.setChildren(oneLevels);
		return JSON.toJSONString(root);
	}

}
