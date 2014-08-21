package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.DataAggregatorFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by benjamin on 19.08.14.
 */
public class AtomClientTest {

    DataAggregatorClient dac;

    @BeforeTest
    public void beforeTest() {
        dac = DataAggregatorFactory.getClient();
        //dac.init();
    }

    @Test
    public void getNotifications() {
        dac.getNotifications("1");
    }
}
