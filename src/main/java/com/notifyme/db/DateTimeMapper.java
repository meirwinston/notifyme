package com.notifyme.db;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DateTimeMapper implements ResultSetMapper<DateTime> {

    @Override
    public DateTime map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Timestamp t = r.getTimestamp(index);
        if(t != null) return new DateTime(r.getTimestamp(index).getTime());
        return null;
    }
}
