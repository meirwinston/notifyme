package com.notifyme;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.notifyme.ws.*;
import com.notifyme.ws.resources.AuthResource;
import com.notifyme.ws.resources.MainResource;
import com.notifyme.ws.resources.OrganizationResource;
import com.notifyme.ws.resources.TopicResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Meir Winston
 */
public class MainModule extends AbstractModule{
    private static final Logger logger = LoggerFactory.getLogger(MainModule.class);
    private ResolvedProperties properties;

    public MainModule(ResolvedProperties properties){
        this.properties = properties;
    }

    @Override
    protected void configure() {
        bind(ResolvedProperties.class).toInstance(this.properties);
        Names.bindProperties(binder(),this.properties);

        install(new DbModule());
        bind(WebServiceApplication.class).asEagerSingleton();
        bind(MainResource.class).asEagerSingleton();
        bind(AuthResource.class).asEagerSingleton();
        bind(TopicResource.class).asEagerSingleton();
        bind(OrganizationResource.class).asEagerSingleton();
        bind(AuthFilter.class).asEagerSingleton();
    }

}
