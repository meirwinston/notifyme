package com.notifyme.db.mappers;

import com.notifyme.db.entities.Organization;
import org.skife.jdbi.v2.BeanMapper;

/**
 * @author Meir Winston
 */
public class OrganizationMapper extends BeanMapper<Organization> {
    public OrganizationMapper() {
        super(Organization.class);
    }
}
