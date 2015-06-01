package com.notifyme.ws.resources;

import com.google.inject.Inject;
import com.notifyme.db.SubscriptionDao;
import com.notifyme.db.TopicDao;
import com.notifyme.db.entities.Subscription;
import com.notifyme.db.entities.Topic;
import com.notifyme.ws.beans.CreateTopicRequest;
import com.notifyme.ws.beans.CreateTopicResponse;
import com.notifyme.ws.beans.SubscribeRequest;
import com.notifyme.ws.beans.SubscribeResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author Meir Winston
 */
@Path("/topic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "/topic", description = "Topic Management Web Services")
public class TopicResource {
    private static final Logger logger = LoggerFactory.getLogger(TopicResource.class);
    private DBI dbi;

    @Inject
    public TopicResource(DBI dbi){
        this.dbi = dbi;
    }

    @ApiOperation(
            value = "Create a new topic",
            notes = "Creates a new topic for an organization",
            response = CreateTopicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 422, message = "Validation failed")
    })
    @Path("create")
    @POST
    public CreateTopicResponse createTopic(CreateTopicRequest request) {
        Topic topic = new Topic();
        topic.setName(request.getName());
        topic.setOrganizationId(request.getOrganizationId());
        long id = dbi.open(TopicDao.class).insert(topic);
        if(id <= 0){
            throw new WebApplicationException("Could not insert");
        }

        return new CreateTopicResponse(id);
    }

    @ApiOperation(
            value = "Subscribe to a topic",
            notes = "Subscribe this account to the specified topic",
            response = CreateTopicResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 422, message = "Validation failed")
    })
    @Path("subscribe")
    @POST
    public SubscribeResponse subscribe(SubscribeRequest request, @Context HttpSession session){
        Subscription subscription = new Subscription();
        subscription.setAccountId((Long)session.getAttribute("accountId"));
        subscription.setTopicId(request.getTopicId());
        subscription.setCreatedDate(new DateTime());
        long id = dbi.open(SubscriptionDao.class).insert(subscription);
        if(id <= 0){
            throw new WebApplicationException("Could not insert, received id: " + id);
        }
        return new SubscribeResponse(id);
    }
}

