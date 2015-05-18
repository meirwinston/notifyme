package com.notifyme.db;

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
}
