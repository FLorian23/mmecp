package de.fhg.fokus.streetlife.configurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by benjamin on 21.08.14.
 */
public class ConfigImpl implements Config {

    @Override
    public Properties getMmecpProps() throws IOException {

        InputStream in = this.getClass().getResourceAsStream("/mmecp.properties");
        Properties props = new Properties();

        props.load(in);

        return props;
    }
}
