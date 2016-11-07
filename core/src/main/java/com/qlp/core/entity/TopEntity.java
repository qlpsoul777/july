package com.qlp.core.entity;

import java.util.Date;

import javax.persistence.Id;

public class TopEntity {
	
	@Id
	private String id;			//主键
	private Date createTime;	//创建时间
	private Date updateTime;	//修改时间
	private String createBy;	//创建人
	private String updateBy;	//修改人
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	

}
