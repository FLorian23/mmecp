package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.atom.Content;
import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import de.fhg.fokus.streetlife.configurator.Constants;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.Channel;

/**
 * Created by benjamin on 19.08.14.
 */
public class AtomClientTest {

	private static final int FIRST_ELEMENT = 0;
	private DataAggregatorClient dac;

	@BeforeTest
	public void beforeTest() throws IOException {
		Properties props = new Properties();
		props.setProperty(Constants.PROPERTY_STORAGE_URL_BASE, "http://streetlifemmecp.apiary-mock.com");
		dac = DataAggregatorFactory.getClient();
		dac.init(props);
	}

	@Test
	public void getNotifications() {
		Feed notification = dac.getNotifications("some Id");
		Assert.assertEquals(notification.getTitle(), "Titel des Weblogs");
	}

	@Test
	public void getChannels() {
		List<Channel> channels = dac.getChannels();
		Assert.assertNotNull(channels.get(FIRST_ELEMENT).isStandard());
	}

	@Test
	public void getNotification() {
		Entry notification = dac.getNotification("some channel id", "some notification id");
		Assert.assertEquals(notification.getTitle(), "Traffic jam");
	}

	@Test
	public void postNotification() throws IOException, URISyntaxException {
		Entry entry = new Entry();
		entry.setTitle("Hello World");
		Content content = new Content();
		content.setType(MediaType.TEXT_HTML_TYPE);
		content.setText("Nothing much");
		entry.setContent(content);
		Response response = dac.postNotification("some channel Id", entry);
		Assert.assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
	}

	@Test
	public void deleteNotification() {
		Assert.assertEquals(dac.deleteNotification("some channel Id", "some notification Id").getStatus(),
				Response.Status.NO_CONTENT.getStatusCode());
	}

	@Test
	public void getChannelForm() {
		Assert.assertEquals(dac.getChannelForm("some channel Id").get(FIRST_ELEMENT).get("value").getTextValue(), "rov_routing");
	}
}
