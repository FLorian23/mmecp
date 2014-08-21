package de.fhg.fokus.streetlife.mmecp.dataaggregator.restclient;

import org.jboss.resteasy.client.ProxyFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

/**
 * Created by benjamin on 19.08.14.
 */
public class AtomClientImpl {

    public String getNotification(String channelId) {
        RegisterBuiltin.register(ResteasyProviderFactory.getInstance());

        AtomClient client = ProxyFactory.create(AtomClient.class, "http://streetlifemmecp.apiary-mock.com");
        String out = client.getNotifications(channelId);
        return out;
    }
}
