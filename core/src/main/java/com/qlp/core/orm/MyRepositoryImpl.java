package com.qlp.core.orm;

/**http://bsdn.org/projects/dorado7/deploy/sample-center/com.bstek.dorado.sample.Main.d#115650*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.transform.ResultTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.CollectionUtils;

import com.qlp.core.utils.ReflectionUtil;
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
	
	public MyRepositoryImpl(JpaEntityInformation<T,ID> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.em = em;
		this.clazz = (Class<T>) entityInformation.getJavaType();
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> queryPageByMap(Map<String, Object> map, Pageable pageable) {
		Criteria c = mapToCriteria(map);
        long total = countCriteriaList(c);
        c.setFirstResult(pageable.getOffset()).setMaxResults(pageable.getPageSize());
        createCriteria(c, pageable.getSort());
		Page<T> page = new PageImpl<T>(c.list(), pageable, total);
        return page;
	}

	private Criteria createCriteria(Criteria criteria, Sort sort) {
        Iterator<?> it;
        if (sort != null) {
            for (it = sort.iterator(); it.hasNext(); ) {
                Sort.Order order = (Sort.Order) it.next();
                if (order.getDirection().equals(Sort.Direction.ASC)) {
                    criteria.addOrder(org.hibernate.criterion.Order.asc(order.getProperty()));
                } else {
                    criteria.addOrder(org.hibernate.criterion.Order.desc(order.getProperty()));
                }
            }
        }
        return criteria;
    }
	

	@SuppressWarnings("unchecked")
	private long countCriteriaList(Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;
        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
        Projection projection = impl.getProjection();
        ResultTransformer resultTransformer = impl.getResultTransformer();
        List<CriteriaImpl.OrderEntry> orderEntries = null;
        orderEntries = (List<OrderEntry>)ReflectionUtil.getFieldValue(impl, "orderEntries");
        ReflectionUtil.setFieldValue(impl, "orderEntries", new ArrayList<Object>());
        //获取记录总数
        Long totalCount = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
        long total = totalCount != null ? totalCount.longValue() : 0L;
        // 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
        c.setProjection(projection);
        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (resultTransformer != null) {
            c.setResultTransformer(resultTransformer);
        }
        ReflectionUtil.setFieldValue(impl, "orderEntries", orderEntries);
        return total;
	}

	/**
	 * map参数转Criteria对象
	 * @param map
	 * @return
	 */
	private Criteria mapToCriteria(Map<String, Object> map) {
		Criteria criteria = createCriteria();
		if(!CollectionUtils.isEmpty(map)){
			for(String key : map.keySet()){
				criteria.add(addConditions(key,map.get(key)));
			}
		}
		
		return criteria;
	}
	
	/**
	 * 
	 * Criterion 是 Criteria 的查询条件。Criteria 提供了 add(Criterion criterion) 方法来添加查询条件。
	 * Criterion 接口的主要实现包括： Example 、 Junction 和 SimpleExpression 。
	 * 而Junction 的实际使用是它的两个子类 conjunction 和 disjunction ，
	 * 分别是使用 AND 和 OR 操作符进行来联结查询条件集合。Criterion 的实例可以通过 Restrictions工具类
	 * 来创建，Restrictions 提供了大量的静态方法，如 eq （等于）、 ge （大于等于）、 between 等方法的
	 * 创建 Criterion 查询条件（SimpleExpression 实例）。除此之外， Restrictions 还提供了方法来
	 * 创建 conjunction 和disjunction 实例，通过往该实例的 add(Criteria) 方法来增加查询条件形成
	 * 一个查询条件集合。
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	private Criterion addConditions(String key, Object object) {
		String[] exp = StringUtil.split(key,"_");
		String suffix = null;
		if(exp.length == 3){
			suffix = exp[2];
		}
		if("a".equals(exp[0])){
			return Restrictions.and(addPredicates(object,exp[1],suffix));
		}else{
			return Restrictions.or(addPredicates(object,exp[1],suffix));
		}
	}

	/**
	 * 添加查询条件
	 * @param object 传入条件值
	 * @param proName 属性名
	 * @param suffix 后缀
	 * 	无 | eq---> 相等
	 * 	ne 	---> 不相等
	 * 	ne 	---> 不相等
	 * 	ne 	---> 不相等
	 * 	ne 	---> 不相等
	 * 	ne 	---> 不相等
	 * 	ne 	---> 不相等
	 * @return
	 */
	private Criterion addPredicates(Object object, String proName, String suffix) {
		if(StringUtil.isBlank(suffix) || StringUtil.equals("eq", suffix)){
			return Restrictions.eq(proName, object);//相等
		}
		if (StringUtil.equals("ne", suffix)) {
            return Restrictions.ne(proName, object);  //不相等
        }
        if (StringUtil.equals("lt", suffix)) {
            return Restrictions.lt(proName, object);  //小于
        }
        if (StringUtil.equals("le", suffix)) {
            return Restrictions.le(proName, object);  //小于等于
        }
        if (StringUtil.equals("gt", suffix)) {
            return Restrictions.gt(proName, object);  //大于
        }
        if (StringUtil.equals("ge", suffix)) {
            return Restrictions.ge(proName, object);  //大于等于
        }
        if (StringUtil.equals("li", suffix)) {
            return Restrictions.like(proName, "%" + object + "%");  //like
        }
        if (StringUtil.equals("nl", suffix)) {
            return Restrictions.not(Restrictions.like(proName, "%" + object + "%"));  //not like
        }
        if (StringUtil.equals("in", suffix)) {
            return Restrictions.in(proName, object);  //in
        }
        if (StringUtil.equals("ni", suffix)) {
            return Restrictions.not(Restrictions.in(proName, object)); //not in
        }
		return null;
	}

	/**
	 * 获取在线Criteria，从session获取；
	 * hibernate还提供离线的DetachedCriteria，创建时无需session,通过自身的静态方法获取。
	 * @return
	 */
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


