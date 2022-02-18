package com.fa.training.group01.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;

public class MyWebAppInitializer implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.getSessionCookieConfig().setHttpOnly(true);
		servletContext.getSessionCookieConfig().setSecure(true);        
		servletContext.addListener(new RequestContextListener());
	}

}
