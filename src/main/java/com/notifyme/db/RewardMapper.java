package com.notifyme.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Meir Winston
 */
public class RewardMapper implements ResultSetMapper<Reward> {
    @Override
    public Reward map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Reward reward = new Reward();
        reward.setRewardId(r.getLong("rewardId"));
        return reward;
    }
}

