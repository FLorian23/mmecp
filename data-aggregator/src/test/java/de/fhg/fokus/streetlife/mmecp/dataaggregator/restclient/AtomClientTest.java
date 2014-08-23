package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import de.fhg.fokus.streetlife.configurator.Constants;
import org.jboss.resteasy.plugins.providers.atom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by benjamin on 19.08.14.
 */
public class AtomClientTest {

	private DataAggregatorClient dac;
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@BeforeTest
	public void beforeTest() throws IOException {
        Properties props = new Properties();
        props.setProperty(Constants.PROPERTY_STORAGE_URL_BASE, "http://streetlifemmecp.apiary-mock.com");
        dac = DataAggregatorFactory.getClient();
        dac.init(props);
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

    @Test
    public void getNotification() {
        String notification = dac.getNotification("some channel id", "some notification id");
        LOG.info(notification);
        Assert.assertNotNull(notification);
    }

    @Test
    public void postNotification() throws IOException, URISyntaxException {
        Feed feed = new Feed();
        feed.setId(new URI("http://example.com/42"));
        feed.setTitle("My Feed");
        feed.setUpdated(new Date());
        Link link = new Link();
        link.setHref(new URI("http://localhost"));
        link.setRel("edit");
        feed.getLinks().add(link);
        feed.getAuthors().add(new Person("Bill Burke"));
        Entry entry = new Entry();
        entry.setTitle("Hello World");
        Content content = new Content();
        content.setType(MediaType.TEXT_HTML_TYPE);
        content.setText("Nothing much");
        entry.setContent(content);
        feed.getEntries().add(entry);
        Response response = dac.postNotification("some channel Id", feed);
        Assert.assertEquals(Response.Status.CREATED.getStatusCode(),response.getStatus());
    }
}
