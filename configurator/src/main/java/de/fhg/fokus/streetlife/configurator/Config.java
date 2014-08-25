package de.fhg.fokus.streetlife.configurator;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by benjamin on 21.08.14.
 */
public interface Config {

    public Properties getMmecpProps() throws IOException;
}
