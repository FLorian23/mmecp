package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import java.util.Properties;

import org.jboss.resteasy.spi.NotImplementedYetException;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient.AtomClientImpl;

/**
 * Created by benjamin on 20.08.14.
 */
public class DataAggregatorClientImpl implements DataAggregatorClient {

	public void init() {
		throw new NotImplementedYetException();
	}

	public void init(Properties props) {
		throw new NotImplementedYetException();
	}

	public String getNotifications(String channelId) {

		AtomClientImpl ac = new AtomClientImpl();
		return ac.getNotification(channelId);
	}

	public String getChannels() {
		AtomClientImpl ac = new AtomClientImpl();
		return ac.getChannels();
	}
}
