package com.qlp.cms.entity;

import com.qlp.core.entity.IdEntity;
import com.qlp.core.enums.StatusEnum;
import com.qlp.enums.TemplateTypeEnum;

/**
 * 模板实体(T_CMS_TEMPLATE)
 * @author qlp
 *
 */
public class Template extends IdEntity{
	
	private String name;				//模板名称
	
	private TemplateTypeEnum type;		//模板类型
	
	private String path;				//发布路径
	
	private Integer sort;				//排序字段
	
	private StatusEnum status;			//模板状态
	
	private String content;				//模板内容
	
	private String introduction;		//模板说明
	
	private Catalog catalog;			//所属栏目
	
	private Site site;					//所属站点
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TemplateTypeEnum getType() {
		return type;
	}

	public void setType(TemplateTypeEnum type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	

}
