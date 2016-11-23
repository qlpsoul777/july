package com.qlp.cms.dao;

import com.qlp.cms.entity.Site;
import com.qlp.core.orm.MyRepository;

public interface SiteDao extends MyRepository<Site, Long>{
	
	public void deleteByIdIn(Long[] ids);

}
