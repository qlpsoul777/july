package com.qlp.core.page;


public class PageRequest extends PageRequestAbstract {

	public PageRequest(int pageSize,int currentPage){
		super(pageSize, currentPage);
	}
	
	@Override
	public Sort getSort() {
		return null;
	}
	
}
