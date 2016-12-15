package com.qlp.cms.dto;

public class StaticFileDto {

	private String name;				//文件名
	private String size;				//文件大小
	private String modifyTime;			//最后修改时间
	private String path;				//相对路径
	private boolean isFile;				//是否是文件
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getModifyTime() {
		return modifyTime;
	}
	
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean getIsFile() {
		return isFile;
	}
	
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	
	
}
