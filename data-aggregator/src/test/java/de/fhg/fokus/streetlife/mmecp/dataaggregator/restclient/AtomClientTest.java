package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;

/**
 * Created by benjamin on 19.08.14.
 */
public class AtomClientTest {

	private DataAggregatorClient dac;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@BeforeTest
	public void beforeTest() {
		dac = DataAggregatorFactory.getClient();
	}

	@Test
	public void getNotifications() {
		String notification = dac.getNotifications("some Id");
		LOG.info(notification);
		Assert.assertNotNull(notification);
	}

	@Test
	public void getChannels() {
		String channels = dac.getChannels();
		LOG.info(channels);
		Assert.assertNotNull(channels);
	}
}
