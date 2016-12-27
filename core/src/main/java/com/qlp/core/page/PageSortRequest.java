package com.qlp.core.page;

import com.qlp.core.utils.AssertUtil;

public class PageSortRequest extends PageRequestAbstract {
	
	private final Sort sort;
	
	public PageSortRequest(int pageSize,int currentPage,Sort sort){
		super(pageSize, currentPage);
		
		AssertUtil.assertNotNull(sort, "排序对象不能为null");
		this.sort = sort;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

}
