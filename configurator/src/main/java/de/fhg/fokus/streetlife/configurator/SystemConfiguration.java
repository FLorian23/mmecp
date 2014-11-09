package de.fhg.fokus.streetlife.configurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by benjamin on 21.08.14.
 */
@ApplicationScoped
public class SystemConfiguration {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private Properties props;

    @PostConstruct
    public void init() throws IOException {
        InputStream in = this.getClass().getResourceAsStream("/mmecp.properties");
        props = new Properties();
        props.load(in);
    }

    public Properties getProperties () {
        return props;
    }

    public URL getStorageBaseUrl() throws MalformedURLException {
        return new URL(props.getProperty("storage.url.base"));
    }

    public URL getWebSocketBaseUrl() throws MalformedURLException {
        return new URL(props.getProperty("mmecp.backend.api.websocket.url"));
    }

    public String getEndPointPanelUi(){
        return props.getProperty("mmecp.backend.api.websocket.endpoint.panelui");
    }


}
