package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import java.util.Properties;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient.AtomClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;

import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import javax.ws.rs.core.Response;

import static de.fhg.fokus.streetlife.configurator.Constants.PROPERTY_MMECP_URL_BASE;

/**
 * Created by benjamin on 20.08.14.
 */
public class DataAggregatorClientImpl implements DataAggregatorClient {

    AtomClient atom;

    @Override
	public void init(Properties props) {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(props.getProperty(PROPERTY_MMECP_URL_BASE));
        atom = target.proxy(AtomClient.class);
	}

    @Override
	public String getNotifications(String channelId) {
		return atom.getNotifications(channelId);
	}

    @Override
    public String getNotification(String channelId, String notificationId) {
        return atom.getNotification(channelId, notificationId);
    }

    @Override
    public String getChannels() {
		return atom.getChannels();
	}

    @Override
    public Response postNotification(String channelId, Feed notification) {
        return atom.postNotification(channelId, notification);
    }
}
