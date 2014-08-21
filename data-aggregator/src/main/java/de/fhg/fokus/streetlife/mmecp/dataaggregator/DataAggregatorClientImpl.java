package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import java.util.Properties;

import de.fhg.fokus.streetlife.configurator.Constants;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient.AtomClient;
import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.NotImplementedYetException;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient.AtomClientImpl;
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
        atom = ProxyFactory.create(AtomClient.class, props.getProperty(PROPERTY_MMECP_URL_BASE));
	}

	public String getNotifications(String channelId) {
		return atom.getNotifications(channelId);
	}

	public String getChannels() {
		return atom.getChannels();
	}
}
