package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.Channel;
import org.codehaus.jackson.JsonNode;
import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Properties;

public interface DataAggregatorClient {

	public void init(Properties props);

	public Feed getNotifications(String channelId);

    public Entry getNotification(String channelId, String notificationId);

	public List<Channel> getChannels();

    public Response postNotification(String channelId, Entry notification);

    public Response deleteNotification(String channelId, String notificationId);

    public JsonNode getChannelForm(String channelId);
}
