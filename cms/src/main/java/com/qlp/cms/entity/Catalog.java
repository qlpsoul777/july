package com.qlp.cms.entity;

import java.util.List;

import com.qlp.core.entity.IdEntity;
import com.qlp.core.enums.ContentTypeEnum;
import com.qlp.core.enums.StatusEnum;

/**
 * 栏目实体(T_CMS_CATALOG)
 * @author july
 *
 */
public class Catalog extends IdEntity{
	
	private String name;				//栏目名
	
	private String alias;				//栏目别名
	
	private StatusEnum status;			//栏目状态
	
	private String introduction;		//栏目描述
	
	private String path;				//访问路径
	
	private ContentTypeEnum type;		//内容类型
	
	private Integer sort;				//排序字段
	
	private String imgPath;				//封面图片
	
	private Site site;					//所属站点
	
	private Catalog parent;				//父级栏目
	
	private List<Catalog> children;		//子栏目

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ContentTypeEnum getType() {
		return type;
	}

	public void setType(ContentTypeEnum type) {
		this.type = type;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Catalog getParent() {
		return parent;
	}

	public void setParent(Catalog parent) {
		this.parent = parent;
	}

	public List<Catalog> getChildren() {
		return children;
	}

	public void setChildren(List<Catalog> children) {
		this.children = children;
	}
	
	public Catalog() {
		super();
	}

	public Catalog(Long id,String name,String alias) {
		super(id);
		this.name = name;
		this.alias =alias;
	}

}
