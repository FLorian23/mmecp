package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by benjamin on 19.08.14.
 */
public interface AtomClient {

	@GET
	@Path("subscription/channel/{channelId}/notification")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String getNotifications(@PathParam("channelId") String channelId);

	@GET
	@Path("subscription/channel")
	public String getChannels();
}
