package com.qlp.enums;

/**
 * 模板类型枚举
 * @author qlp
 *
 */
public enum TemplateTypeEnum {
	
	INDEX(0,"首页模板"),
	LIST(1,"列表模板"),
	CONTENT(2,"信息详情模板"),
	OTHER(3,"其他模板")
	;
	
	private final int code;
	private final String desc;
	
	private TemplateTypeEnum(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getDesc(){
		return desc;
	}

}
