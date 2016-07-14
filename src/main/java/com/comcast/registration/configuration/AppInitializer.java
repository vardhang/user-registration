package com.comcast.registration.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Vardhana Rao Gude on 7/13/2016.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected String[] getServletMappings()
	{
		return new String[] {"/"};
	}

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class<?>[0];
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class<?>[] {RegistrationWebMvcConfig.class};
	}
}
