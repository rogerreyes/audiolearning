package com.ud.webapp.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

import ru.xpoft.vaadin.SpringVaadinServlet;

public interface WebConfig {

	@WebListener
	static class SpringContextLoaderListener extends ContextLoaderListener {
	}

	@WebListener
	static class SpringRequestContextListener extends RequestContextListener {
	}

	@WebServlet(initParams = { @WebInitParam(name = "beanName", value = "audioLearningUI") }, urlPatterns = { "/*",
			"/VAADIN/*" })
	@SuppressWarnings("serial")
	static class MainServlet extends SpringVaadinServlet {
	}

}
