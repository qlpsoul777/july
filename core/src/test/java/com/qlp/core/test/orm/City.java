package com.qlp.core.test.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.qlp.core.entity.BaseEntity;

@Entity
@Table(name = "city")
public class City extends BaseEntity{
	
	private Long id;
	private String name;
	private String code;
	private String dist;
	private Long popu;
	
	@Id
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="CountryCode")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="District")
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	
	@Column(name="Population")
	public Long getPopu() {
		return popu;
	}
	public void setPopu(Long popu) {
		this.popu = popu;
	}
	
	
}
