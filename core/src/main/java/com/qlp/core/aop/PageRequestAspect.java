package com.qlp.core.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qlp.core.annotation.PageRequestParam;
import com.qlp.core.page.PageRequest;
import com.qlp.core.page.Pageable;
import com.qlp.core.utils.DataConvertUtil;
import com.qlp.core.utils.LogUtil;
import com.qlp.core.utils.StringUtil;

@Aspect
public class PageRequestAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(PageRequestAspect.class);
	
	@Pointcut("execution(public com...*Controller.list(..))")
	public void pointCut(){}
	
	@Before(value = "pointCut() && @annotation(annotation) &&args(object,..)",argNames="annotation,object")
	public void covertToPageable(ProceedingJoinPoint pj, PageRequestParam annotation, Object object) throws Throwable{
		long start = System.currentTimeMillis();
		
		Object[] o = pj.getArgs();
		HttpServletRequest request = (HttpServletRequest) o[0];
		Enumeration<String> params = request.getAttributeNames();
		String key;
		Object value;
		int pageSize = annotation.size(),currentPage = 0;
		while(params.hasMoreElements()){
			key = params.nextElement();
			value = request.getParameter(key);
			if(StringUtil.equals("pageSize", key)){
				pageSize = DataConvertUtil.toInt(value, 10);
			}else if(StringUtil.equals("currentPage", key)){
				currentPage = DataConvertUtil.toInt(value);
			}
			
		}
		Pageable pageable = new PageRequest(pageSize, currentPage);
		
		request.setAttribute(annotation.name(), pageable);
		
		long end = System.currentTimeMillis();
		
		LogUtil.info(logger, "组装分页查询条件耗时：{0}毫秒,组装后的结果：{1}", end-start,pageable);
		pj.proceed();
	}

}
