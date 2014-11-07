package de.fhg.fokus.streetlife.mmecp.dataaggregator.generic;

import de.fhg.fokus.streetlife.configurator.MMECPConfig;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.api.AtomClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.impl.ChannelImpl;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.json.ChannelBean;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.Channel;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by benjamin on 20.08.14.
 */
public class DataAggregatorClientImpl implements DataAggregatorClient {

    private static final ObjectMapper OM = new ObjectMapper();
    private final static Logger LOG = LoggerFactory.getLogger(DataAggregatorClientImpl.class);
    private AtomClient atom;
    @Inject
    @MMECPConfig("storage.url.base")
    String storageBaseUrl;

    public static <T> T convert(JsonNode node, Class<T> clazz) {
        T obj = null;

        try {

            obj = OM.readValue(node, clazz);

        } catch (JsonParseException e) {

            LOG.error("deserialize json node ({}): {}", e.getMessage(), node);

        } catch (JsonMappingException e) {

            LOG.error("deserialize json node({}): {}", e.getMessage(), node);

        } catch (IOException e) {

            LOG.error("deserialize json node ({}): {}", e.getMessage(), node);

        }

        return obj;
    }

    @Override
    public void init() {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(storageBaseUrl);
        atom = target.proxy(AtomClient.class);
    }

    @Override
    public Feed getNotifications(String channelId) {
        return atom.getNotifications(channelId);
    }

    @Override
    public Entry getNotification(String channelId, String field, String order) {
        return atom.getNotification(channelId, field, order);
    }

    @Override
    public List<Channel> getChannels() {

        List<Channel> channels = new ArrayList<Channel>();

        JsonNode node = atom.getChannels();

        for (JsonNode channelNode : node) {
            channels.add(new ChannelImpl(convert(channelNode, ChannelBean.class)));
        }

        return channels;
    }

    @Override
    public Response postNotification(String channelId, Entry notification) {
        return atom.postNotification(channelId, notification);
    }

    @Override
    public Response deleteNotification(String channelId, String notificationId, boolean force) {
        return atom.deleteNotification(channelId, notificationId, force);
    }

    @Override
    public JsonNode getChannelForm(String channelId) {
        return atom.getChannelForm(channelId);
    }
}
