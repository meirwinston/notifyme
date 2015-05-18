package com.notifyme;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.notifyme.db.TestDao;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author Meir Winston
 */
public class DbModule extends AbstractModule {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void configure() {
        bind(TestDao.class).asEagerSingleton();
        bind(DBI.class).toProvider(DBIProvider.class);
    }

    static class DBIProvider implements Provider<DBI> {
        Properties systemProperties = new Properties();
        @Override
        public DBI get() {
            DBI dbi = new DBI(systemProperties.getProperty("jdbc.crm.url"),
                    systemProperties.getProperty("jdbc.crm.username"),
                    systemProperties.getProperty("jdbc.crm.password"));

            return dbi;
        }
    }
}
