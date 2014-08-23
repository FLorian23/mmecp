package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import org.jboss.resteasy.plugins.providers.atom.Feed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by benjamin on 19.08.14.
 */
public interface AtomClient {

	@GET
	@Path("subscription/channel/{channelId}/notification")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String getNotifications(@PathParam("channelId") String channelId);

    @POST
    @Path("subscription/channel/{channelId}/notification")
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    public Response postNotification(@PathParam("channelId") String channelId, Feed notification);

    @GET
    @Path("subscription/channel/{channelId}/notification/{notificationId}")
    @Produces(MediaType.APPLICATION_ATOM_XML)
    public String getNotification(@PathParam("channelId") String channelId, @PathParam("notificationId") String notificationId);

	@GET
	@Path("subscription/channel")
	public String getChannels();
}
