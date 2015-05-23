package com.notifyme.db;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 */
public class DbFactoryParams {
    private static final Logger logger = LoggerFactory.getLogger(DbFactoryParams.class);
    private static final String NS = "db.";
    public static final String DS = NS + "datasource_type";
    public static final String URL = NS + "datasource.url";
    // NS + datasource._ dynamic datasource bean bindings.
    public static final String AUTO_COMMIT = NS + "auto_commit";
    public static final String CONN_TIMEOUT = NS + "conn_timeout";
    public static final String IDLE_TIMEOUT = NS + "max_idle";
    public static final String MAX_POOL_SIZE = NS + "max_pool_size";
    public static final String MIN_IDLE = NS + "min_idle";
    public static final String POOL_NAME = NS + "pool_name";
    public static final String ENABLE_JMX = NS + "enable_jmx";
    public static final String ENABLE_METRICS = NS + "enable_metrics";
    public static final String ENABLE_STMT_COMMENTS = NS + "enable_stmt_comments";
    public static final String SCAN_LEGACY_HASH = NS + "scan_legacy_hash";
    public static final String CACHE_QUERY_REWRITES = NS + "cache_query_rewrites";

    private static final String HIKARI_POOLNAME = "poolName";
    private static final String OUR_DS_PREFIX = NS + "datasource";
    private static final String HIKARI_DS_PREFIX = "dataSource";

    public static String prefixed(String prefix, String key) {
        return prefix + "." + key;
    }

    private static Map<String,String> HIKARI_MAPPINGS = ImmutableMap.<String,String>builder()
            .put(AUTO_COMMIT, "autoCommit")
            .put(CONN_TIMEOUT, "connectionTimeout")
            .put(DS, "dataSourceClassName")
            .put(IDLE_TIMEOUT, "idleTimeout")
            .put(MAX_POOL_SIZE, "maximumPoolSize")
            .put(MIN_IDLE, "minimumIdle")
            .put(POOL_NAME, "poolName")
            .put(ENABLE_JMX, "registerMbeans")
            .build();

    private static Map<String,String> DS_SPECIFIC_DEFAULTS = ImmutableMap.of("com.mysql.jdbc.jdbc2.optional.MysqlDataSource", "mysql-defaults.properties");

    private static Set<String> REQUIRED = ImmutableSet.of();

    public static String defaultsFor(String datasource) {
        return DS_SPECIFIC_DEFAULTS.get(datasource);
    }

    public static HikariConfig getHikariConfig(String prefix, Properties p) {
        Properties scrubbed = new Properties();
        Set<String> missing = Sets.newHashSet(REQUIRED);
        for(Map.Entry<Object,Object> entry : p.entrySet()) {
            if(entry.getKey().toString().startsWith(prefix)) {
                String key = entry.getKey().toString().substring(prefix.length() + 1);
                missing.remove(key);
                String value = entry.getValue().toString();
                if(HIKARI_MAPPINGS.containsKey(key)) {
                    scrubbed.put(HIKARI_MAPPINGS.get(key), value);
                }
                else if(key.startsWith(OUR_DS_PREFIX)) {
                    // remove the db.
                    scrubbed.put(HIKARI_DS_PREFIX + key.substring(OUR_DS_PREFIX.length()), value);
                }
            }
        }
        if(!missing.isEmpty()) {
            StringBuilder err = new StringBuilder("These properties are required to create a datasource for ")
            .append(prefix).append(", but were not set:\n");
            for (String m : missing) {
                err.append(String.format("\t%s%n", m));
            }
            throw new IllegalStateException(err.toString());
        }
        if(!scrubbed.containsKey(HIKARI_POOLNAME)) {
            scrubbed.setProperty(HIKARI_POOLNAME, prefix);
        }
        if(logger.isDebugEnabled()) {
            StringBuilder all = new StringBuilder();
            for(Map.Entry<Object,Object> a : scrubbed.entrySet()) {
                all.append("\n\t").append(a.getKey()).append("=").append(a.getValue());
            }
            logger.debug("Connecting to DB {} with properties: {}", prefix, all);
        }
        return new HikariConfig(scrubbed);
    }
}
