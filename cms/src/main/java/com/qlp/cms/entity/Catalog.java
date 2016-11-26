package com.qlp.cms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.qlp.core.converter.StatusEnumConverter;
import com.qlp.core.entity.IdEntity;
import com.qlp.core.enums.ContentTypeEnum;
import com.qlp.core.enums.StatusEnum;

/**
 * 栏目实体
 * @author july
 *
 */
@Entity
@Table(name = "T_CMS_CATALOG")
public class Catalog extends IdEntity{
	
	private String name;				//栏目名
	private String alias;				//栏目别名
	private StatusEnum status;			//栏目状态
	private String introduction;		//栏目简介
	private String path;				//访问路径
	private ContentTypeEnum type;		//内容类型
	private Integer sort;				//排序字段
	private String imgPath;				//封面图片
	
	private Site site;					//所属站点
	private Catalog parent;				//父级栏目
	
	private List<Catalog> children;		//子栏目

	@Column(name = "catalog_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "catalog_alias")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "catalog_status")
	@Convert( converter = StatusEnumConverter.class )
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "catalog_path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "catalog_type")
	@Enumerated(value = EnumType.STRING)
	public ContentTypeEnum getType() {
		return type;
	}

	public void setType(ContentTypeEnum type) {
		this.type = type;
	}
	
	@Column(name = "catalog_sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "site_id")
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Catalog getParent() {
		return parent;
	}

	public void setParent(Catalog parent) {
		this.parent = parent;
	}

	@OneToMany(targetEntity = Catalog.class, cascade = { CascadeType.ALL }, mappedBy = "parent")
	@Fetch(value = FetchMode.SUBSELECT)
	@OrderBy("sort desc")
	public List<Catalog> getChildren() {
		return children;
	}

	public void setChildren(List<Catalog> children) {
		this.children = children;
	}
	
	public Catalog() {
		super();
	}

	public Catalog(String name) {
		super();
		this.name = name;
	}

}
