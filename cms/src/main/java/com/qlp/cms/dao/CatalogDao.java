package com.qlp.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.qlp.cms.entity.Catalog;
import com.qlp.core.orm.MyRepository;

public interface CatalogDao extends MyRepository<Catalog, Long> {
	
	@Query("select c from Catalog c where c.parent.id is null and c.site.id = ?1")
	List<Catalog> findCatalogTree(Long siteId);

}
