package com.qlp.cms.dao;

import com.qlp.cms.entity.Site;
import com.qlp.core.page.Page;
import com.qlp.core.page.Pageable;


public interface SiteDao{
	
	public void deleteByIdIn(Long[] ids);

	public Site findOne(Long id);

	public Site save(Site site);

	public Page<Site> findAll(Site site, Pageable pageable);

}
