package com.qlp.cms.entity;

import java.util.List;

import com.qlp.core.entity.IdEntity;
import com.qlp.core.enums.StatusEnum;

public class Catalog extends IdEntity{
	
	private String name;	//栏目名
	private String alias;	//栏目别名
	private StatusEnum status;	//栏目状态
	private String introduction;	//栏目简介
	
	private Site site;	//所属站点
	private Catalog parent;	//父级栏目
	private List<Catalog> children;		//子栏目

}
