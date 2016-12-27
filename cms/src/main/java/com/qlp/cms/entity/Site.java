package com.qlp.cms.entity;

import com.qlp.core.entity.IdEntity;
import com.qlp.core.enums.StatusEnum;
/**
 * 站点实体(T_CMS_SITE)
 * @author july
 *
 */
public class Site extends IdEntity{
	
	private String name;			//站点名称
	
	private String num;				//站点编号
	
	private StatusEnum status;		//站点状态
	
	private String path;			//站点访问路径
	
	private String introduction;	//站点简介
	
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
	
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
