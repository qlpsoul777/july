package com.qlp.core.page;

import java.util.List;

public interface Page<T> {
	
	/**
	 * 判断分页数据是否为null
	 * @return
	 */
	boolean hasContent();
	
	/**
	 * 分页数据
	 * @return
	 */
	List<T> getContent();
	
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
	 * 总页数
	 * @return
	 */
	int getTotalPage();
	
	/**
	 * 数据总数
	 * @return
	 */
	long getTotalElements();

}
