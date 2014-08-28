package de.fhg.fokus.streetlife.mmecp.dataaggregator;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.DataAggregatorClientImpl;

/**
 * Created by benjamin on 20.08.14.
 */
public abstract class DataAggregatorFactory {

    public static synchronized DataAggregatorClient getClient() {

        DataAggregatorClient client = new DataAggregatorClientImpl();

        return client;
    }
}
