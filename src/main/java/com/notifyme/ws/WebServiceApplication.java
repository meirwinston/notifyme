package com.notifyme.ws;

import com.google.inject.Inject;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @author Meir Winston
 */
public class WebServiceApplication extends Application<Configuration> {
    final static Logger logger = LoggerFactory.getLogger(WebServiceApplication.class);

    @Inject
    private MainResource mainResource;

    @Inject
    private AuthFilter authFilter;

    public WebServiceApplication(){}

    @Override
    public String getName() {
        return "notifyme";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        logger.info("initialize");
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        logger.info("run");
        environment.jersey().register(mainResource);

        environment.servlets()
                .addFilter("AuthFilter", authFilter)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/ws/*");

    }
}
