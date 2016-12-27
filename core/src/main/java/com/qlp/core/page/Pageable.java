package com.qlp.core.page;

public interface Pageable {
	
	/**
	 * 每页显示条数
	 * @return
	 */
	int getPageSize();
	
	/**
	 * 当前页(从0开始计)
	 * @return
	 */
	int getcurrentPage();
	
	/**
	 * 偏移量
	 * @return
	 */
	int getOffset();
	
	/**
	 * 排序条件
	 * @return
	 */
	Sort getSort();

}
