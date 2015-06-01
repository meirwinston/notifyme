package com.notifyme.db;

import com.notifyme.db.entities.Account;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @author Meir Winston
 */
public interface AccountDao {
    @SqlQuery("select * from H5GSocialCRM.Rewards")
    @Mapper(RewardMapper.class)
    List<Reward> getRewards();

    @SqlQuery("select id from accounts where username = :username and password = :password")
    long getAccountId(@Bind String username, @Bind String password);

    @GetGeneratedKeys
    @SqlUpdate("insert into accounts(username, password, phoneNumber, countryCode, createdDate) values (:username, :password, :phoneNumber, :countryCode, :createdDate)")
    long insert(@BindBean Account req);
}
