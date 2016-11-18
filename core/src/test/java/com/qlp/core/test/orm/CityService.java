package com.qlp.core.test.orm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qlp.core.Exception.ErrorDetail.SysErrorlEnum;
import com.qlp.core.utils.AssertUtil;

@Service("cityService")
public class CityService {
	
	@Autowired
	private CityDao cityDao;
	
	public List<City> findAll(){
		
		return cityDao.findAll();
	}

}
