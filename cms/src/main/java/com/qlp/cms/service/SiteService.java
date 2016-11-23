package com.qlp.cms.service;

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
import com.qlp.core.utils.DataConvertUtil;
import com.qlp.core.utils.LogUtil;
import com.qlp.core.utils.StringUtil;

@Service("siteService")
@Transactional(readOnly = true)
public class SiteService {
	
	private static final Logger logger = LoggerFactory.getLogger(SiteService.class);

	@Autowired
	private SiteDao siteDao;
	
	/**
	 * 通过主键id查询单条数据
	 * @param id 主键
	 * @return 未查询到返回null
	 */
	public Site query(String id){
		LogUtil.info(logger, "接收到的参数id:{0}", id);
		Site site = null;
		if(StringUtil.isNotBlank(id)){
			site = siteDao.findOne(DataConvertUtil.toLong(id));
		}
		LogUtil.info(logger, "通过参数id:{0}-查询到的结果site:{1}", id,site);
		return site;
	}
	
	/**
	 * 新增、修改的保存
	 * @param site 待持久化对象
	 * @return 已持久化对象
	 */
	@Transactional(readOnly = false)
	public Site save(Site site){
		LogUtil.info(logger, "待持久化对象参数site:{0}", site);
		return siteDao.saveAndFlush(site);
	}
	
	/**
	 * 通过主键id集合删除数据
	 * @param ids 主键集合以逗号分隔的字符串
	 */
	@Transactional(readOnly = false)
	public void deleteByIds(String ids){
		LogUtil.info(logger, "待删除ids:{0}", ids);
		if(StringUtil.isNotBlank(ids)){
			Long[] id = StringUtil.toLongArray(ids,",");
			siteDao.deleteByIdIn(id);
			LogUtil.info(logger, "已删除:{0}条数据", id.length);
		}
	}
	
	/**
	 * QBE	通过对象参数和分页条件查询分页信息
	 * @param site 对象参数
	 * @param pageable 分页条件
	 * @return 分页结果
	 */
	public Page<Site> queryPageBySite(Site site,Pageable pageable){
		ExampleMatcher matcher = ExampleMatcher.matching()     
				  .withIgnorePaths("createTime")
		.withIgnoreNullValues()
		.withMatcher("name",GenericPropertyMatchers.contains())
		.withMatcher("status", GenericPropertyMatchers.exact())
		.withMatcher("num", GenericPropertyMatchers.exact());
	
		Example<Site> example = Example.of(site, matcher);
		return siteDao.findAll(example, pageable);
	}

}
