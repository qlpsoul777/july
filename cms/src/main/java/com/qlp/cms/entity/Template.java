package com.qlp.cms.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qlp.core.converter.StatusEnumConverter;
import com.qlp.core.entity.IdEntity;
import com.qlp.core.enums.StatusEnum;
import com.qlp.enums.TemplateTypeEnum;

/**
 * 模板实体
 * @author qlp
 *
 */
@Entity
@Table(name = "T_CMS_TEMPLATE")
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
	
	@Column(name = "template_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "template_type")
	@Enumerated(value = EnumType.ORDINAL)
	public TemplateTypeEnum getType() {
		return type;
	}

	public void setType(TemplateTypeEnum type) {
		this.type = type;
	}

	@Column(name = "template_path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "template_sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "template_status")
	@Convert( converter = StatusEnumConverter.class )
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	@Lob
	@Column(name = "template_content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "catalog_id")
	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	

}
