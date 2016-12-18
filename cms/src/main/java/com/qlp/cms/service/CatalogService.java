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
import com.qlp.core.utils.ConstantsUtil;
import com.qlp.core.utils.LogUtil;

/**
 * 栏目管理服务层
 * @author qlp
 *
 */
@Service("catalogService")
@Transactional(readOnly = true)
public class CatalogService {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);
	
	@Autowired
	private CatalogDao catalogDao;

	/**
	 * 查询站点下所有栏目json数据
	 * @param site
	 * @return
	 */
	public String queryCatalogTree(Site site) {
		List<Catalog> oneLevels = catalogDao.findCatalogTree(site.getId());
		Catalog root = new Catalog(0L,site.getName(),null);
		root.setChildren(oneLevels);
		
		String result = JSON.toJSONString(root);
		LogUtil.info(logger, "栏目管理栏目树json数据：{0}", result);
		return result;
	}

	/**
	 * 保存栏目数据
	 * @param catalog
	 * @return
	 */
	@Transactional(readOnly = false)
	public Catalog save(Catalog catalog){
		LogUtil.info(logger, "待持久化参数catalog:{0}", catalog);
		return catalogDao.saveAndFlush(catalog);
	}
	
	/**
	 * 根据主键删除栏目
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public String delete(Long id){
		LogUtil.info(logger, "待删除catalog主键:{0}", id);
		
		catalogDao.delete(id);
		catalogDao.flush();
		
		LogUtil.info(logger, "删除catalog成功");
		return ConstantsUtil.SUCCESS;
	}

	/**
	 * 根据主键查询栏目
	 * @param id
	 * @return
	 */
	public Catalog get(Long id) {
		LogUtil.info(logger, "接收到的Catalog参数id:{0}", id);
		Catalog catalog = catalogDao.findOne(id);
		LogUtil.info(logger, "通过参数id:{0}-查询到的结果catalog:{1}", id,catalog);
		return catalog;
	}

	/**
	 * 根据站点信息、父级栏目信息、栏目别名获取栏目路径
	 * @param site
	 * @param parent
	 * @param alias
	 * @return
	 */
	public String getPath(Site site, Catalog parent, String alias) {
		LogUtil.info(logger, "获取栏目路径时接收到的参数site:{0},parent:{1},alias:{2}", site,parent,alias);
		String path;
		if(parent == null){
			path = CmsConstant.OVER_CHAR + site.getNum() + CmsConstant.OVER_CHAR + alias;
		}else{
			path = parent.getPath() + CmsConstant.OVER_CHAR + alias;
		}
		LogUtil.info(logger, "根据站点信息、父级栏目信息、栏目别名获取栏目路径为:{0}", path);
		return path;
	}

}
