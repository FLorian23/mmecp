package de.fhg.fokus.streetlife.mmecp.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/atom")
public class SubscriptionApi {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{subscriberId}/{notificationId}")
	public String getSubscriberNotification(@PathParam("subscriberId") long subscriberId, @PathParam("notificationId") long notificationId) {

		return "Hello World!: " + subscriberId + ", " + notificationId;
	}
}
