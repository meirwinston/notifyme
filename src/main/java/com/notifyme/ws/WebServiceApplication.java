package com.notifyme.ws;

import com.google.inject.Inject;
import com.notifyme.ResolvedProperties;
import com.notifyme.ws.resources.AuthResource;
import com.notifyme.ws.resources.MainResource;
import com.notifyme.ws.resources.OrganizationResource;
import com.notifyme.ws.resources.TopicResource;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author Meir Winston
 */
public class WebServiceApplication extends Application<Configuration> {
    final static Logger logger = LoggerFactory.getLogger(WebServiceApplication.class);

    @Inject
    private MainResource mainResource;

    @Inject
    private AuthResource authResource;

    @Inject
    private AuthFilter authFilter;

    @Inject
    private ResolvedProperties properties;

    @Inject
    private TopicResource topicResource;

    @Inject
    private OrganizationResource organizationResource;

    @Inject
    public WebServiceApplication(){
    }

    @Override
    public String getName() {
        return "notifyme";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        logger.info("initialize");
        bootstrap.addBundle(new AssetsBundle("/swagger/", "/swagger")); //swagger
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        logger.info("run");
        // Register the custom ExceptionMapper
        environment.jersey().register(new GenericExceptionMapper());

        environment.jersey().register(mainResource);
        environment.jersey().register(authResource);
        environment.jersey().register(topicResource);
        environment.jersey().register(organizationResource);


        //to be able to get session: request.getSession()
        environment.servlets().setSessionHandler(new SessionHandler());

//        environment.servlets()
//                .addFilter("AuthFilter", authFilter)
//                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/ws/*");
        runSwagger(configuration, environment);


    }

    private void runSwagger(Configuration configuration, Environment environment) throws IOException {
        // Swagger Resource
        environment.jersey().register(new ApiListingResourceJSON());

        // Swagger providers
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner());

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader());


        // Set the swagger config options
        SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0.1");

        if("public".equals(properties.get("swagger.url"))){
            //the address to which web services will be pointed at
            config.setBasePath("http://" + getPulicIP() + ":" + properties.get("swagger.port"));
        }
        else{
            //the address to which web services will be pointed at
            config.setBasePath("http://" + properties.get("swagger.url") + ":" + properties.get("swagger.port"));
        }

        logger.info("SWAGGER METHODS POINT AT ADDRESS {}", config.getBasePath());
    }

    private static String getPulicIP() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));

        String ip = in.readLine(); //you get the IP as a String
        return ip;
    }

}
