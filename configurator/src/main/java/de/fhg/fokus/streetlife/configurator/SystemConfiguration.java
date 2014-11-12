package de.fhg.fokus.streetlife.configurator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	/**
	 * Get the basis URL for the storage.
	 *
	 * @return The storage URL as URL
	 * @throws MalformedURLException
	 *             Thrown if the URL is not a valid URL
	 */
    public URL getStorageBaseUrl() throws MalformedURLException {
        return new URL(props.getProperty("storage.url.base"));
    }

	/**
	 * Get the basis URL for the websocket.
	 *
	 * @return The websocket URL as URL
	 * @throws MalformedURLException
	 *             Thrown if the URL is not a valid URL
	 */
    public URL getWebSocketBaseUrl() throws MalformedURLException {
        return new URL(props.getProperty("mmecp.backend.api.websocket.url"));
    }

	/**
	 * Get the relative context path of the the PanelUi endpoint.
	 *
	 * @return The panelUi endpoint context path as String
	 */
    public String getEndPointPanelUi(){
        return props.getProperty("mmecp.backend.api.websocket.endpoint.panelui");
    }

}
