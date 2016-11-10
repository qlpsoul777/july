package com.qlp.core.orm;

/**http://bsdn.org/projects/dorado7/deploy/sample-center/com.bstek.dorado.sample.Main.d#115650*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.qlp.core.utils.StringUtil;

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
	private final Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public MyRepositoryImpl(JpaEntityInformation<T,ID> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
		this.clazz = (Class<T>) entityInformation.getClass();
	}

	/**
	 * 通过map参数查询列表
	 * 	map中的key值是对应实体对象的属性加后缀的形式
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryByMap(Map<String, Object> map) {
		Criteria criteria = mapToCriteria(map);
		return (List<T>)criteria.list();
	}

	/**
	 * map参数转Criteria对象
	 * @param map
	 * @return
	 */
	private Criteria mapToCriteria(Map<String, Object> map) {
		Criteria criteria = createCriteria();
		
		for(String key : map.keySet()){
			
		}
		return criteria;
	}
	
	public Criteria createCriteria() {
		Criteria criteria = getSession().createCriteria(clazz);
		return criteria;
	}
	 
	/**
	 * 从EntityManager获取hibernate的session
	 * @return
	 */
	private Session getSession() {
		return (Session) this.em.getDelegate();
	}
 

}


