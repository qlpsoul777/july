package com.qlp.core.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qlp.core.page.Sort.Direction;
import com.qlp.core.utils.AssertUtil;

public class Sort{
	
	private static final Direction DEFAULT_DIRECTION = Direction.ASC;
	private final List<Order> orders;
	
	public Sort(List<Order> orders){
		AssertUtil.assertNotBlank(orders, "排序条件不能为blank");
		
		this.orders = orders;
	}
	
	public Sort(Order ... orders){
		this(Arrays.asList(orders));
	}
	
	public Sort(Direction desc, String ... properties) {
		List<Order> orders = new ArrayList<>(properties.length);
		for (String property : properties) {
			orders.add(new Order(desc,property));
		}
		this.orders = orders;
	}

	public List<Order> getOrders(){
		return orders;
	}
	
	public static enum Direction{
		ASC,DESC;
	}
	
	
	public static class Order{
		
		private final Direction direction;
		private final String property;
		
		public Order(Direction direction,String property){
			AssertUtil.assertNotBlank(property,"排序字段不能为blank");
			
			this.direction = (direction == null?DEFAULT_DIRECTION:direction);
			this.property = property;
		}
		
		
	}


}
