package com.notifyme.db;

import com.notifyme.db.entities.Organization;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * @author Meir Winston
 */
public interface OrganizationDao {

    @GetGeneratedKeys
    @SqlUpdate("insert into organizations(name) values(:name)")
    long insert(@BindBean Organization organization);
}
