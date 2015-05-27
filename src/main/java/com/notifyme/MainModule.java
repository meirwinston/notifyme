package com.notifyme;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.notifyme.ws.AuthFilter;
import com.notifyme.ws.AuthResource;
import com.notifyme.ws.MainResource;
import com.notifyme.ws.WebServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Meir Winston
 */
public class MainModule extends AbstractModule{
    private static final Logger logger = LoggerFactory.getLogger(MainModule.class);
    private ResolvedProperties properties;

    public MainModule(ResolvedProperties properties){
        this.properties = properties;
    }

    private static String env = "dev";

    @Override
    protected void configure() {
        Names.bindProperties(binder(),this.properties);

        install(new DbModule());
        bind(WebServiceApplication.class).asEagerSingleton();
        bind(MainResource.class).asEagerSingleton();
        bind(AuthResource.class).asEagerSingleton();
        bind(AuthFilter.class).asEagerSingleton();
    }

//    private static ResolvedProperties loadProperties() throws IOException {
//        InputStream in = MainModule.class.getClassLoader().getResourceAsStream(env + "/config.properties");
//        ResolvedProperties properties = new ResolvedProperties();
//        properties.load(in);
//        logger.info("PROPERTIES: {}", properties);
//        return properties;
//    }
//
//    public static void main(String[] args){
//        try {
//            String ymlPath = MainModule.class.getClassLoader().getResource(env + "/config.yml").getPath();
//            logger.info("config.yml path {}", ymlPath);
//            Injector injector = Guice.createInjector(new MainModule(loadProperties()));
//            injector.getInstance(WebServiceApplication.class).run(new String[]{"server",ymlPath});
//            logger.info("STARTED {}", injector);
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//            logger.error(e.getMessage(), e);
//        }
//    }
}
