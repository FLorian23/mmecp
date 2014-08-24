package de.fhg.fokus.streetlife.mmecp.services;

import de.fhg.fokus.streetlife.configurator.Config;
import de.fhg.fokus.streetlife.configurator.ConfigFactory;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.Channel;
import org.codehaus.jackson.JsonNode;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/subscription")
public class SubscriptionApi {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private DataAggregatorClient dac;

    public SubscriptionApi() {
        init();
    }

    public void init() {
        Config config = ConfigFactory.getConfig();
        dac = DataAggregatorFactory.getClient();
        try {
            dac.init(config.getMmecpProps());
        } catch (IOException e) {
            LOG.error("Error while loading config: {}", e.getStackTrace());
        }
    }

    @GET
    @Produces("application/atom+xml")
    @Path("channel/{channelId}/notification")
    public Feed getChannelNotifications(@PathParam("channelId") String channelId) {
        return dac.getNotifications(channelId);
    }

    @POST
    @Consumes("application/atom+xml")
    @Path("channel/{channelId}/notification")
    public Response postChannelNotification(@PathParam("channelId") String channelId, Feed notification) {
        LOG.info("Posting new notification with title ({} [{}]) to channel ({})", notification.getTitle(), notification.getId(), channelId);
        return dac.postNotification(channelId, notification);
    }

    @GET
    @Produces("application/atom+xml")
    @Path("channel/{channelId}/notification/{notificationId}")
    public Feed getChannelNotification(@PathParam("channelId") String channelId, @PathParam("notificationId") long notificationId) {
        return dac.getNotification(channelId, channelId);
    }

    @GET
    @Produces("application/json")
    @Path("channel")
    public List<Channel> getChannels() {
        return dac.getChannels();
    }

    @DELETE
    @Path("channel/{channelId}/notification/{notificationId}")
    public Response deleteNotification(@PathParam("channelId") String channelId, @PathParam("notificationId") String notificationId) {
        LOG.info("Deleting notification ({}) from channel ({})", notificationId, channelId);
        return dac.deleteNotification(channelId, notificationId);
    }

    @GET
    @Produces("application/json")
    @Path("form/{channelId}")
    public JsonNode getChannelForm(@PathParam("channelId") String channelId) {
        return dac.getChannelForm(channelId);
    }

}
