package de.fhg.fokus.streetlife.configurator;

/**
 * Created by benjamin on 21.08.14.
 */
public abstract class ConfigFactory {

    public static synchronized Config getConfig() {

        Config config = new ConfigImpl();

        return config;
    }
}
