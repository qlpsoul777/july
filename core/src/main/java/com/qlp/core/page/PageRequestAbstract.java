package com.qlp.core.page;

import com.qlp.core.utils.AssertUtil;

public abstract class PageRequestAbstract implements Pageable{
	
	private final int pageSize;
	private final int currentPage;
	
	public PageRequestAbstract(int pageSize,int currentPage){
		AssertUtil.assertTrue(pageSize >= 1, "每页显示记录数不能小于1");
		AssertUtil.assertTrue(currentPage >= 0, "当前页不能小于0");
		
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getcurrentPage() {
		return currentPage;
	}

	@Override
	public int getOffset() {
		return currentPage * pageSize;
	}

}
