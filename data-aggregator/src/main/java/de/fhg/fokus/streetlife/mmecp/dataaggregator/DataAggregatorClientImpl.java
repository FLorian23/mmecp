package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient.AtomClient;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient.AtomClientImpl;
import org.jboss.resteasy.spi.NotImplementedYetException;

import java.util.Properties;

/**
 * Created by benjamin on 20.08.14.
 */
public class DataAggregatorClientImpl implements DataAggregatorClient {

    @Override
    public void init() {
        throw new NotImplementedYetException();
    }

    @Override
    public void init(Properties props) {
        throw new NotImplementedYetException();
    }

    @Override
    public String getNotifications(String channelId) {

        AtomClientImpl ac = new AtomClientImpl();

        return ac.getNotification(channelId);
    }
}
