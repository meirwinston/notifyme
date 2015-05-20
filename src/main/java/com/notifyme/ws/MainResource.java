package com.notifyme.ws;

import org.skife.jdbi.v2.sqlobject.BindBean;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

/**
 * @author Meir Winston
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MainResource {
    @Path("sayHello")
    @GET
    public Saying sayHello() {
        return new Saying(1, "Hello");
    }

    @Path("login")
    @POST
    public LoginResponse login(@BindBean LoginRequest loginRequest) {
        return new LoginResponse(UUID.randomUUID().toString());
    }
}
