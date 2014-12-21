package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.Channel;
import org.codehaus.jackson.JsonNode;
import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;

import javax.ws.rs.core.Response;
import java.util.List;

public interface DataAggregatorClient {

	public void init();

	public Feed getNotifications(String channelId);

    public Entry getNotification(String channelId, String field, String order);

	public List<Channel> getChannels();

    public Response postNotification(String channelId, Entry notification);

    public Response deleteNotification(String channelId, String notificationId, boolean force);

    public JsonNode getChannelForm(String channelId);

    public JsonNode parseResponse(String response);
}
