package com.notifyme.db;

import com.notifyme.bean.LoginRequest;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import java.util.List;

/**
 * @author Meir Winston
 */
public interface TestDao {
    @SqlQuery("select * from H5GSocialCRM.Rewards")
    @Mapper(RewardMapper.class)
    List<Reward> getRewards();

    @SqlQuery("select count(username) > 0 from accounts where username = :username and password = :password")
    boolean usernameAndPasswordExist(@BindBean LoginRequest req);
}
