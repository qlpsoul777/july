package com.qlp.core.test.orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.qlp.core.Exception.ErrorDetail.BusiErrorEnum;
import com.qlp.core.utils.AssertUtil;
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
		map.put("a_name", "Nagasaki");
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
	
	@Test
	public void findPageByMap(){
		Map<String,Object> map = new HashMap<>();
		map.put("a_code", "BRA");
		Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "code"));
		Page<City> pageInfo = cityService.findPageByMap(map, pageable);
		AssertUtil.assertNotBlank(map, BusiErrorEnum.OUTPUT_NOT_FOUND, "查询结果为空");
		LogUtil.info(logger, "分页查询结果总页数：{0}\n总条数：{1}\n内容：{2}", pageInfo.getTotalPages(),pageInfo.getTotalElements(),pageInfo.getContent().toString());
	}

}
