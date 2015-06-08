package com.notifyme.db.mappers;

import com.notifyme.db.entities.Topic;
import org.skife.jdbi.v2.BeanMapper;

/**
 * @author Meir Winston
 */
public class TopicMapper extends BeanMapper<Topic> {
    public TopicMapper() {
        super(Topic.class);
    }
}
