package com.qlp.cms.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.qlp.core.entity.TopEntity;

@Entity
@Table(name = "T_CMS_SITE")
public class Site extends TopEntity{
	
	private String name;
	private String num;
	private int status;
	private String path;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
