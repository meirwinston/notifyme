package com.notifyme.db;

import com.notifyme.db.entities.Topic;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author Meir Winston
 */
public interface TopicDao {
    @SqlUpdate("insert into topics(name, organizationId) values(:name, :organizationId)")
    int insert(@BindBean Topic topic);
}
