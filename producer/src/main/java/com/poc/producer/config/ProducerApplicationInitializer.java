package com.poc.producer.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.logging.Logger;

@Configuration
public class ProducerApplicationInitializer implements ServletContextInitializer {

    private static final Logger logger= Logger.getLogger(ProducerApplicationInitializer.class.getName());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        logger.info("INITIALIZING MY DISPATCHER");

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(ProducerConfig.class);


        // Creating a dispatcher servlet object
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);

        // Registering Dispatcher Servlet with Servlet Context
        ServletRegistration.Dynamic myCustomDispatcherServlet = servletContext.addServlet("producerServlet", dispatcherServlet);

        // Setting load on startup
        myCustomDispatcherServlet.setLoadOnStartup(1);

        // Enable async request processing
        myCustomDispatcherServlet.setAsyncSupported(true);

        // Adding mapping url
        myCustomDispatcherServlet.addMapping("/prod/*");
    }
}
