package com.notifyme.ws;

import com.google.inject.Inject;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Meir Winston
 */
public class WebServiceApplication extends Application<Configuration> {
    final static Logger logger = LoggerFactory.getLogger(WebServiceApplication.class);

    @Inject
    private MainResource mainResource;

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
    }
}
