package com.notifyme.ws;

import com.google.inject.Inject;
import com.notifyme.bean.LoginRequest;
import com.notifyme.bean.LoginResponse;
import com.notifyme.db.TestDao;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.Api;
import org.skife.jdbi.v2.DBI;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Meir Winston
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/auth", description = "Authentication Web Services")
public class AuthResource {
    private DBI dbi;

    @Inject
    public AuthResource(DBI dbi){
        this.dbi = dbi;
    }

    @ApiOperation(
            value = "Login",
            notes = "Login to system",
            response = LoginResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 422, message = "Validation failed")
    })
    @Path("login")
    @POST
    public LoginResponse login(LoginRequest loginRequest, @Context HttpServletRequest request) {
        if(!dbi.open(TestDao.class).usernameAndPasswordExist(loginRequest)){
            throw new NotAuthorizedException("Incorrect username and/or password");
        }
        return new LoginResponse(request.getSession().getId());
    }
}
