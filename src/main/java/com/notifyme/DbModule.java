package com.notifyme;

import com.google.common.base.Optional;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.notifyme.db.DateTimeMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.dropwizard.jdbi.args.JodaDateTimeArgumentFactory;
import io.dropwizard.jdbi.args.JodaDateTimeMapper;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimeZone;

/**
 * @author Meir Winston
 */
public class DbModule extends AbstractModule {
    static final Logger logger = LoggerFactory.getLogger(DbModule.class);

    @Override
    protected void configure() {
        bind(DBI.class).toProvider(DBIProvider.class);
    }

    static class DBIProvider implements Provider<DBI> {
        @Inject
        @Named("db.datasource.url")
        private String dbUrl;

        @Inject
        @Named("db.datasource.user")
        private String dbUsername;

        @Inject
        @Named("db.datasource.password")
        private String dbPassword;

        @Override
        public DBI get() {
            logger.info("{} {} []", dbUrl, dbUsername, dbPassword);
            HikariConfig cfg = new HikariConfig();
            cfg.setPassword(dbPassword);
            cfg.setUsername(dbUsername);
            cfg.setJdbcUrl(dbUrl);
            HikariDataSource db = new HikariDataSource(cfg);
            DBI dbi = new DBI(db);
            dbi.registerMapper(new JodaDateTimeMapper(Optional.of(TimeZone.getTimeZone("UTC"))));
            dbi.registerArgumentFactory(new JodaDateTimeArgumentFactory());

            return dbi;
        }
    }
}
