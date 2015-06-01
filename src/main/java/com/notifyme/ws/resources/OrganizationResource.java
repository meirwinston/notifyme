package com.notifyme.ws.resources;

import com.google.inject.Inject;
import com.notifyme.db.OrganizationDao;
import com.notifyme.db.entities.Organization;
import com.notifyme.ws.beans.*;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * @author Meir Winston
 */
@Path("/organization")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/organization", description = "Organization Management Web Services")
public class OrganizationResource {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationResource.class);
    private DBI dbi;

    @Inject
    public OrganizationResource(DBI dbi){
        this.dbi = dbi;
    }

    @ApiOperation(
            value = "Create a new organization",
            notes = "Creates a new organization",
            response = CreateOrganizationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 422, message = "Validation failed")
    })
    @Path("create")
    @POST
    public CreateOrganizationResponse createOrganization(CreateOrganizationRequest request) {
        Organization organization = new Organization();
        organization.setName(request.getName());
        long id = dbi.open(OrganizationDao.class).insert(organization);
        if(id <= 0){
            throw new WebApplicationException("Could not insert");
//            throw new RuntimeException("Could not insert");
        }

        return new CreateOrganizationResponse(id);
    }

}


