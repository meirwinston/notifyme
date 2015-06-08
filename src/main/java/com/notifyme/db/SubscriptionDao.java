package com.notifyme.db;

import com.notifyme.db.entities.Subscription;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.util.List;

/**
 * @author Meir Winston
 */
public interface SubscriptionDao {
    @SqlUpdate("insert into subscriptions(accountId,topicId, createdDate) values(:accountId, :topicId, :createdDate)")
    int insert(@BindBean Subscription subscription);

    @SqlQuery("select * from subscriptions where topicId=:topicId limit :offset, :count")
    List<Subscription> select(@Bind("topicId") long topicId, @Bind("offset") int offset, @Bind("count") int count);

}
