package com.notifyme.ws.resources;

import com.notifyme.ws.Saying;
import com.wordnik.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Meir Winston
 */
@Path("/ws")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/ws", description = "Authentication Web Services")
public class MainResource {
    @Path("sayHello")
    @GET
    public Saying sayHello() {
        return new Saying(1, "Hello");
    }

}
