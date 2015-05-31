package com.notifyme.ws.resources;

import com.google.inject.Inject;
import com.neovisionaries.i18n.CountryCode;
import com.notifyme.db.entities.Account;
import com.notifyme.ws.beans.LoginRequest;
import com.notifyme.ws.beans.LoginResponse;
import com.notifyme.ws.beans.SignupRequest;
import com.notifyme.ws.beans.SignupResponse;
import com.notifyme.db.AccountDao;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(AuthResource.class);

    private DBI dbi;

    @Inject
    public AuthResource(DBI dbi) {
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
        long id = dbi.open(AccountDao.class).getAccountId(loginRequest.getUsername(), loginRequest.getPassword());
        if (id <= 0) {
            throw new NotAuthorizedException("Incorrect username and/or password");
        }
        return new LoginResponse(request.getSession().getId());
    }

    @ApiOperation(
            value = "Singup",
            notes = "Creates a new account in the system",
            response = SignupResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "Validation failed")
    })
    @Path("signup")
    @POST
    public SignupResponse signup(SignupRequest signupRequest, @Context HttpServletRequest request) {
        Account account = new Account();
        account.setCreatedDate(new DateTime());
        account.setCountryCode(CountryCode.valueOf(signupRequest.getCountryCode()));
        account.setPassword(signupRequest.getPassword());
        account.setPhoneNumber(signupRequest.getPhoneNumber());
        account.setUsername(signupRequest.getUsername());
        long id = dbi.open(AccountDao.class).insert(account);
        if (id <= 0) {
            throw new WebApplicationException("Could not insert");
        }

        return new SignupResponse(id);
    }
}
