package com.qlp.core.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public class TopEntity {
	
	@Id
    @GeneratedValue(generator = "system-uuid")    
    @GenericGenerator(name = "system-uuid", strategy = "uuid")   
	private String uuid;		//主键
	
	@Column(updatable = false)
	private Date createTime = new Date();	//创建时间
	
	private Date updateTime;	//修改时间
	
	private String createBy;	//创建人
	
	private String updateBy;	//修改人
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
