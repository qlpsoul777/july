package com.qlp.core.test.orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.qlp.core.utils.CollectionUtil;
import com.qlp.core.utils.LogUtil;


public class CityServiceTest extends BaseJdbcTest{
	
	private static final Logger logger = LoggerFactory.getLogger(CityServiceTest.class);
	
	@Autowired
	private CityService cityService;

	@Test
	public void testFindByMap() {
		Map<String,Object> map = new HashMap<>();
		map.put("a_name_li", "Al");
		List<City> list = cityService.findByMap(map);
		if(CollectionUtil.isNotBlank(list)){
			for (City city : list) {
				LogUtil.info(logger, "city:{0}", city.toString());
			}
		}
	}
	
	@Test
	public void testFindByMapAndSort() {
		Map<String,Object> map = new HashMap<>();
		map.put("a_code", "AFG");
		map.put("o_code", "BRA");
		map.put("o_name", "Cairo");
		Sort sort = new Sort(Direction.ASC, "popu");
		List<City> list = cityService.findByMap(map,sort);
		if(CollectionUtil.isNotBlank(list)){
			for (City city : list) {
				LogUtil.info(logger, "city:{0}", city.toString());
			}
		}
	}
	
	@Test
	public void testFindByCriteria(){
		List<City> list = cityService.findByCriteria();
		if(CollectionUtil.isNotBlank(list)){
			for (City city : list) {
				LogUtil.info(logger, "city:{0}", city.toString());
			}
		}
	}

}
