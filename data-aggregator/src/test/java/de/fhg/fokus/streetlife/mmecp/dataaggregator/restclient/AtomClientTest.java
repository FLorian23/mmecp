package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import org.testng.annotations.Test;

/**
 * Created by benjamin on 19.08.14.
 */
@Test
public class AtomClientTest {

    public void getNotifications() {
        AtomClientImpl client = new AtomClientImpl();
        client.getNotification("1");
    }
}
