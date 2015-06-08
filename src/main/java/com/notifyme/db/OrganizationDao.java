package com.notifyme.db;

import com.notifyme.db.entities.Organization;
import com.notifyme.db.entities.Topic;
import com.notifyme.db.mappers.OrganizationMapper;
import com.notifyme.db.mappers.TopicMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * @author Meir Winston
 */
public interface OrganizationDao {

    @GetGeneratedKeys
    @SqlUpdate("insert into organizations(name) values(:name)")
    long insert(@BindBean Organization organization);

    @Mapper(OrganizationMapper.class)
    @SqlQuery("select * from organizations where name like :name limit :offset, :count")
    List<Organization> findByName(@Bind("name") String name,@Bind("offset") int offset,@Bind("count") int count);

}
