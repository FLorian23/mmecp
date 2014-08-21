package de.fhg.fokus.streetlife.mmecp.services;

import de.fhg.fokus.streetlife.configurator.Config;
import de.fhg.fokus.streetlife.configurator.ConfigFactory;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

@Path("/subscription")
public class SubscriptionApi {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private DataAggregatorClient dac;

    @PostConstruct
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
    @Produces(MediaType.APPLICATION_ATOM_XML)
    @Path("channel/{channelId}/notification")
    public String getChannelNotifications(@PathParam("channelId") String channelId) {

        return dac.getNotifications(channelId);
    }

    @GET
    @Produces(MediaType.APPLICATION_ATOM_XML)
    @Path("channel/{channelId}/notification/{notificationId}")
    public String getChannelNotification(@PathParam("channelId") String channelId, @PathParam("notificationId") long notificationId) {

        return "Specific notification: channelId " + channelId + ", notificationId " + notificationId;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("channel")
    public String getChannels() {
        init();
        return dac.getChannels();
    }

}
