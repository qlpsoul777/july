package com.qlp.core.test.orm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("cityService")
public class CityService {
	
	@Autowired
	private CityDao cityDao;
	
	public List<City> findByMap(Map<String,Object> map){
		return cityDao.queryByMap(map);
	}
	
	public List<City> findByMap(Map<String,Object> map,Sort sort){
		return cityDao.queryByMap(map,sort);
	}
	
	public List<City> findByCriteria(){
		return cityDao.queryByCriteria();
	}
	
	public Page<City> findPageByMap(Map<String,Object> map,Pageable pageable){
		return cityDao.queryPageByMap(map, pageable);
	}

}
