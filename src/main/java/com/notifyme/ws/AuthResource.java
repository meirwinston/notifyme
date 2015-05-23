package com.notifyme.ws;

import com.google.inject.Inject;
import com.notifyme.bean.LoginRequest;
import com.notifyme.bean.LoginResponse;
import com.notifyme.db.TestDao;
import org.skife.jdbi.v2.DBI;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Meir Winston
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
    private DBI dbi;

    @Inject
    public AuthResource(DBI dbi){
        this.dbi = dbi;
    }
    @Path("login")
    @POST
    public LoginResponse login(LoginRequest loginRequest, @Context HttpServletRequest request) {
        if(!dbi.open(TestDao.class).usernameAndPasswordExist(loginRequest)){
            throw new NotAuthorizedException("Incorrect username and/or password");
        }
        return new LoginResponse(request.getSession().getId());
    }
}
