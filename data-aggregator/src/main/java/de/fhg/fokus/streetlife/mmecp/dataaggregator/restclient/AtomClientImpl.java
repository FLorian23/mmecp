package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by benjamin on 19.08.14.
 */
public class AtomClientImpl {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("deprecation")
	public String getNotification(String channelId) {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());

		AtomClient client = ProxyFactory.create(AtomClient.class, "http://streetlifemmecp.apiary-mock.com");
		String out = client.getNotifications(channelId);
		LOG.debug("Get channels for ID: " + channelId);
		return out;
	}

	@SuppressWarnings("deprecation")
	public String getChannels() {
		RegisterBuiltin.register(ResteasyProviderFactory.getInstance());

		AtomClient client = ProxyFactory.create(AtomClient.class, "http://streetlifemmecp.apiary-mock.com");
		String out = client.getChannels();
		LOG.debug("Get channels");
		return out;
	}
}
