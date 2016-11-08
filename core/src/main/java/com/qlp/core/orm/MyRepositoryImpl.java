package com.qlp.core.orm;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 数据操作实现类MyRepositoryImpl
 * 	--->继承SimpleJpaRepository(实现JpaRepository申明的方法)
 * 	--->实现MyRepository接口自定义的方法
 * 
 * @author qlp
 * @param <T> 持久化对象实体 eg:User user;
 * @param <ID> 实体主键类型eg:String,Long...
 */
@NoRepositoryBean
public class MyRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements MyRepository<T,ID>{

	private final EntityManager em;
	
	public MyRepositoryImpl(JpaEntityInformation entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
	}

	@Override
	public List<T> queryByMap(Map<String, Object> map) {
		
		return null;
	}

 

}


