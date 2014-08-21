package de.fhg.fokus.streetlife.mmecp.services;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;

@Path("/subscription")
public class SubscriptionApi {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	private DataAggregatorClient dac;

	@PostConstruct
	private void init() {
		LOG.info("Init....");
		dac = DataAggregatorFactory.getClient();
	}

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Path("channel/{channelId}/notification")
	public String getChannelNotitifications(@PathParam("channelId") long channelId) {

		return "Channel notifications: channelId " + channelId;
	}

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Path("channel/{channelId}/notification/{notificationId}")
	public String getChannelNotification(@PathParam("channelId") long channelId, @PathParam("notificationId") long notificationId) {

		return "Specific notification: channelId " + channelId + ", notificationId " + notificationId;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("channel")
	public String getChannels() {
		return dac.getChannels();
	}

}
