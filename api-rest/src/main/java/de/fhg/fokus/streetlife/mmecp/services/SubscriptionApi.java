package de.fhg.fokus.streetlife.mmecp.services;

import de.fhg.fokus.streetlife.configurator.Config;
import de.fhg.fokus.streetlife.configurator.ConfigFactory;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.io.IOException;

@Path("/subscription")
public class SubscriptionApi {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private DataAggregatorClient dac;

    public void init() {
        Config config = ConfigFactory.getConfig();
        dac = DataAggregatorFactory.getClient();
        try {
            dac.init(config.getMmecpProps());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces("application/atom+xml")
    @Path("channel/{channelId}/notification")
    public String getChannelNotifications(@PathParam("channelId") String channelId) {
        init();
        return dac.getNotifications(channelId);
    }

    @POST
    @Consumes("application/atom+xml")
    @Path("channel/{channelId}/notification")
    public Response postChannelNotification(@PathParam("channelId") String channelId, Feed notification) {
        init();
        dac.postNotification(channelId, notification);
        LOG.info("Posting new notification with title \""+notification.getTitle()+"\" to channel \"" + channelId + "\"");
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces("application/atom+xml")
    @Path("channel/{channelId}/notification/{notificationId}")
    public String getChannelNotification(@PathParam("channelId") String channelId, @PathParam("notificationId") long notificationId) {
        init();
        return "Specific notification: channelId " + channelId + ", notificationId " + notificationId;
    }

    @GET
    @Produces("application/json")
    @Path("channel")
    public String getChannels() {
        init();
        return dac.getChannels();
    }

}
