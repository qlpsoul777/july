package com.qlp.core.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 * @author qlp
 *
 */
public final class CollectionUtil {
	
	/**
	 * 判断集合是否为null
	 * @param col
	 * @return
	 */
	public static boolean isNull(Collection<?> col){
		return null== col;
	}
	
	/**
	 * 判断集合是否为blank
	 * @param col
	 * @return
	 */
	public static boolean isBlank(Collection<?> col){
		return (null== col) || (col.isEmpty());
	}
	
	/**
	 * 判断Map是否为null
	 * @param col
	 * @return
	 */
	public static boolean isNull(Map<?,?> map){
		return null== map;
	}
	
	/**
	 * 判断Map是否为blank
	 * @param map
	 * @return
	 */
	public static boolean isBlank(Map<?,?> map){
		return (null== map) || (map.isEmpty());
	}

}
