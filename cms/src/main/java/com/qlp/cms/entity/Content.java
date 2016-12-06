package com.qlp.cms.entity;

import java.util.Date;

import com.qlp.core.entity.IdEntity;
import com.qlp.enums.ArticleTypeEnum;

public class Content extends IdEntity{
	
	private String title;					//新闻标题
	private String subTitle;				//副标题
	private ArticleTypeEnum type;			//新闻类型
	
	
	private Date publishTime;				//
	private Integer isRefer;				//
	private String referName;				//
	private String sourceFrom;				//
	private String imgPath;					//
	private String keyWords;				//
	private String path;					//
	private String contentText;				//
	private String introduction;			//
	private Integer sort;					//
	private Catalog catalog;				//			
	private Site site;						//				

}
