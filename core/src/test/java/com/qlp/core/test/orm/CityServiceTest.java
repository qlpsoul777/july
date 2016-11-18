package com.qlp.core.test.orm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class CityServiceTest extends BaseJdbcTest{
	
	@Autowired
	private CityService cityService;

	@Test
	public void testFindAll() {
		System.out.println(cityService.findAll().size());
	}

}
