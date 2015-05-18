package com.notifyme;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.notifyme.ws.MainResource;
import com.notifyme.ws.WebServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Meir Winston
 */
public class MainModule extends AbstractModule{
    private static final Logger logger = LoggerFactory.getLogger(MainModule.class);

    @Override
    protected void configure() {
        install(new DbModule());
        bind(WebServiceApplication.class).asEagerSingleton();
        bind(MainResource.class).asEagerSingleton();
    }

    public static void main(String[] args){
        try {
            Injector injector = Guice.createInjector(new MainModule());
            injector.getInstance(WebServiceApplication.class).run(new String[]{"server","src/main/resources/notifyme.yml"});
            logger.info("STARTED {}", injector);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
