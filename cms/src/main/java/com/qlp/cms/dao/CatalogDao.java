package com.qlp.cms.dao;

import java.util.List;

import com.qlp.cms.entity.Catalog;

public interface CatalogDao{
	
	List<Catalog> findCatalogTree(Long siteId);

	Catalog save(Catalog catalog);

	void delete(Long id);

	Catalog findOne(Long id);

}
