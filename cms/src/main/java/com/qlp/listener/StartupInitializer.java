package com.qlp.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qlp.cache.GlobalCache;
import com.qlp.core.enums.EnvironmentEnum;
import com.qlp.core.utils.StringUtil;

public class StartupInitializer implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		GlobalCache.env = event.getServletContext().getInitParameter("spring.profiles.default");
		String path = event.getServletContext().getRealPath(File.separator);
		if(StringUtil.equals(GlobalCache.env, EnvironmentEnum.DEPLOY.getCode())){
			path = event.getServletContext().getInitParameter("dataPath");
		}
		GlobalCache.dataPath = path;
		
	}

}
