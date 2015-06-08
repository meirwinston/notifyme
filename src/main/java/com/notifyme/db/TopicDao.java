package com.notifyme.db;

import com.notifyme.db.entities.Topic;
import com.notifyme.db.mappers.TopicMapper;
import org.skife.jdbi.v2.BeanMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * @author Meir Winston
 */
public interface TopicDao {
    @SqlUpdate("insert into topics(name, organizationId) values(:name, :organizationId)")
    int insert(@BindBean Topic topic);

    @Mapper(TopicMapper.class)
    @SqlQuery("select * from topics where organizationId=:organizationId limit :offset, :count")
    List<Topic> select(@Bind("organizationId") long organizationId,@Bind("offset") int offset,@Bind("count") int count);
}
