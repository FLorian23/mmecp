package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import org.jboss.resteasy.plugins.providers.atom.Feed;

import javax.ws.rs.core.Response;
import java.util.Properties;

public interface DataAggregatorClient {

	public void init(Properties props);

	public String getNotifications(String channelId);

    public String getNotification(String channelId, String notificationId);

	public String getChannels();

    public Response postNotification(String channelId, Feed notification);
}
