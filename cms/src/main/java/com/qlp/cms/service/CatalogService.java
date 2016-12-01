package com.qlp.cms.service;

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
import com.qlp.constant.CmsConstant;

@Service("catalogService")
@Transactional(readOnly = true)
public class CatalogService {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);
	
	@Autowired
	private CatalogDao catalogDao;

	public String queryCatalogTree(Site site) {
		List<Catalog> oneLevels = catalogDao.findCatalogTree(site.getId());
		Catalog root = new Catalog(0L,site.getName(),null);
		root.setChildren(oneLevels);
		return JSON.toJSONString(root);
	}

	public Catalog getParent(Long pId,Site site) {
		Catalog catalog = catalogDao.findOne(pId);
		if(null == catalog){
			catalog = new Catalog(0L,site.getName(),CmsConstant.OVER_CHAR + site.getNum());
		}
		return catalog;
	}
	
	@Transactional(readOnly = false)
	public Catalog save(Catalog catalog){
		return catalogDao.saveAndFlush(catalog);
	}
	
	@Transactional(readOnly = false)
	public boolean delete(Long id){
		catalogDao.delete(id);
		catalogDao.flush();
		return true;
	}

	public Catalog get(Long pId) {
		return catalogDao.findOne(pId);
	}

	public String getPath(Site site, Catalog parent, String alias) {
		String path =null;
		if(parent == null){
			path = CmsConstant.OVER_CHAR + site.getNum() + CmsConstant.OVER_CHAR + alias;
		}else{
			path = parent.getPath() + CmsConstant.OVER_CHAR + alias;
		}
		return path;
	}

}
