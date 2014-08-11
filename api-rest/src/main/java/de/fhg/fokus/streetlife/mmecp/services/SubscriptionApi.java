package de.fhg.fokus.streetlife.mmecp.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/subscription")
public class SubscriptionApi {

    @GET
    @Produces(MediaType.APPLICATION_ATOM_XML)
    @Path("channel/{channelId}/notification")
    public String getChannelNotitifications(@PathParam("channelId") long channelId) {

        return "Channel notifications: channelId " + channelId;
    }

	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	@Path("/channel/{channelId}/notification/{notificationId}")
	public String getChannelNotification(@PathParam("channelId") long channelId, @PathParam("notificationId") long notificationId) {

		return "Specific notification: channelId " + channelId + ", notificationId " + notificationId;
	}
}
