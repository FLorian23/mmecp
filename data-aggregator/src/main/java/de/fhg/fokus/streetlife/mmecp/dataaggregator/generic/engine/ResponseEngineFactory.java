package de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.engine;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.EngineType;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.ResponseParseEngine;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;

/**
 * Created by bdi on 21/12/14.
 */
@ApplicationScoped
public class ResponseEngineFactory implements Serializable {

    @Produces
    @ResponseParseEngineMethod(EngineType.FIWARE)
    public ResponseParseEngine getFiWareEngine() {
        return new FiWareEngine();
    }
}
