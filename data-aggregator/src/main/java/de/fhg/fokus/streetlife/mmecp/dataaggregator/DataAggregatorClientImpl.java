package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import java.util.Properties;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient.AtomClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.NotImplementedYetException;

import org.jboss.resteasy.spi.ResteasyProviderFactory;

import static de.fhg.fokus.streetlife.configurator.Constants.PROPERTY_MMECP_URL_BASE;

/**
 * Created by benjamin on 20.08.14.
 */
public class DataAggregatorClientImpl implements DataAggregatorClient {

    AtomClient atom;

	public void init() {
		throw new NotImplementedYetException();
	}

	public void init(Properties props) {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(props.getProperty(PROPERTY_MMECP_URL_BASE));
        atom = target.proxy(AtomClient.class);
	}

	public String getNotifications(String channelId) {
		return atom.getNotifications(channelId);
	}

	public String getChannels() {
		return atom.getChannels();
	}
}
