package com.qlp.cms.service;


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

import com.qlp.cms.dao.TemplateDao;
import com.qlp.cms.entity.Template;
import com.qlp.core.utils.LogUtil;

@Service("templateService")
@Transactional(readOnly = true)
public class TemplateService {
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateService.class);
	
	@Autowired
	private TemplateDao templateDao;

	
	/**
	 * 通过主键id查询单条数据
	 * @param id 主键
	 * @return 未查询到返回null
	 */
	public Template query(Long id){
		LogUtil.info(logger, "接收到Template的参数id:{0}", id);
		Template template = templateDao.findOne(id);
		LogUtil.info(logger, "通过参数id:{0}-查询到的结果template:{1}", id,template);
		return template;
	}
	
	/**
	 * 新增、修改的保存
	 * @param template 待持久化对象
	 * @return 已持久化对象
	 */
	@Transactional(readOnly = false)
	public Template save(Template template){
		LogUtil.info(logger, "待持久化参数template:{0}", template);
		return templateDao.saveAndFlush(template);
	}
	
	public Page<Template> queryPageBySite(Template template, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching()     
				  .withIgnorePaths("createTime")
		.withIgnoreNullValues()
		.withMatcher("name",GenericPropertyMatchers.contains())
		.withMatcher("status", GenericPropertyMatchers.exact())
		.withMatcher("type", GenericPropertyMatchers.exact());
	
		Example<Template> example = Example.of(template, matcher);
		return templateDao.findAll(example, pageable);
	}

	public Template newIfNotFound(Long id) {
		Template template = null;
		if(id != null){
			template = query(id);
		}
		if(template == null){
			template = new Template();
		}
		return template;
	}
	
	

}
