package com.notifyme.db;

import com.notifyme.db.entities.Subscription;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author Meir Winston
 */
public interface SubscriptionDao {
    @SqlUpdate("insert into subscriptions(accountId,topicId, createdDate) values(:accountId, :topicId, :createdDate)")
    int insert(@BindBean Subscription subscription);

}
