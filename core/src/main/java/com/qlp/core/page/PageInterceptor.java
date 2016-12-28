package com.qlp.core.page;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class})})
public class PageInterceptor implements Interceptor{
	
	private String dataBaseType;

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler delegate = (StatementHandler) invocation.getTarget();
		BoundSql boundSql=delegate.getBoundSql();
		Object object = boundSql.getParameterObject();
		if(object instanceof Pageable){
			Pageable pageable = (Pageable) object;
			
		}
		
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		this.dataBaseType = properties.getProperty("dataBaseType");
	}
	
	
	
	

}
