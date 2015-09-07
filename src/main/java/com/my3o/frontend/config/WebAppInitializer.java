package com.my3o.frontend.config;

import java.util.Set;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import com.my3o.frontend.web.interceptor.My3oUrlFilter;

/**
 * @author Alexander Duckardt
 * 
 */
public class WebAppInitializer implements WebApplicationInitializer {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
     * .ServletContext)
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        servletContext.setInitParameter("spring.profiles.default", "local");
        servletContext.addListener(new Log4jConfigListener());
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.scan("com.my3o.common.config", "com.my3o.backend.config", "com.my3o.frontend.config");

        servletContext.addListener(new ContextLoaderListener(appContext));

        servletContext.addFilter("My3oUrlFilter", My3oUrlFilter.class).addMappingForUrlPatterns(null, false, "/*");

        ServletRegistration.Dynamic appServlet = servletContext.addServlet("My3O", new DispatcherServlet(appContext));
        appServlet.setLoadOnStartup(1);

//        appServlet.setMultipartConfig(new MultipartConfigElement(System
//                .getProperty("catalina.base") + "/upload", 81920000l,
//                10230000l, 8000000));

        Set<String> mappingConflicts = appServlet.addMapping("/");
        if (!mappingConflicts.isEmpty()) {
            throw new IllegalStateException("'appServlet' could not be mapped to '/' due "
                    + "to an existing mapping. This is a known issue under Tomcat versions "
                    + "<= 7.0.14; see https://issues.apache.org/bugzilla/show_bug.cgi?id=51278");
        }
    }
}
