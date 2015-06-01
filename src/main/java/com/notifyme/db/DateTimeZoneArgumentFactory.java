package com.notifyme.db;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.joda.time.format.FormatUtils;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Scott Smith
 */
public class DateTimeZoneArgumentFactory implements ArgumentFactory<DateTime> {

    @Override
    public boolean accepts(Class<?> expectedType, Object value, StatementContext ctx) {
        return value instanceof DateTimeZone;
    }

    @Override
    public Argument build(Class<?> expectedType, DateTime value, StatementContext ctx) {
        if (value == null) {
            return new Argument() {
                @Override
                public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {

                }
            };
        }
        return new Argument() {
            @Override
            public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {

            }
        };
    }


}
