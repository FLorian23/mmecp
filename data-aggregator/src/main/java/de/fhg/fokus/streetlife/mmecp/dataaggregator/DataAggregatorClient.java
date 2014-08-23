package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import org.jboss.resteasy.plugins.providers.atom.Feed;

import javax.ws.rs.core.Response;
import java.util.Properties;

public interface DataAggregatorClient {

	public void init(Properties props);

	public Feed getNotifications(String channelId);

    public Feed getNotification(String channelId, String notificationId);

	public String getChannels();

    public Response postNotification(String channelId, Feed notification);

    public Response deleteNotification(String channelId, String notificationId);
}
