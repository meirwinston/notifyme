package com.notifyme.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Meir Winston
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {
    @Path("sayHello")
    @GET
    public Saying sayHello() {
        return new Saying(1, "Hello");
    }

}
