package com.qlp.cms.dao;

import com.qlp.cms.entity.Template;
import com.qlp.core.page.Page;
import com.qlp.core.page.Pageable;


public interface TemplateDao{

	Template findOne(Long id);

	Template save(Template template);

	Page<Template> findAll(Template template, Pageable pageable);

}
