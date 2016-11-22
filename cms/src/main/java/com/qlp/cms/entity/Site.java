package com.qlp.cms.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qlp.core.converter.StatusEnumConverter;
import com.qlp.core.entity.IdEntity;
import com.qlp.core.enums.StatusEnum;

@Entity
@Table(name = "T_CMS_SITE")
public class Site extends IdEntity{
	
	private String name;	//站点名称
	
	private String num;		//站点编号
	
	private StatusEnum status = StatusEnum.ENABLE;		//站点状态
	
	private String path;		//站点访问路径
	
	private String introduction;	//站点简介
	
	@Column(name = "site_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "site_no")
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	@Column(name = "site_status")
	@Convert( converter = StatusEnumConverter.class )
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	@Column(name = "site_path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name = "introduction")
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
