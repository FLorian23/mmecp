package de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.engine;

import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.EngineType;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.ResponseParseEngine;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created by bdi on 21/12/14.
 */
@SessionScoped
public class ResponseEngineFactory implements Serializable {

    private EngineType engineType;

    public ResponseParseEngine getResponseParseEngine() {
        ResponseParseEngine responseParseEngine;
        switch (engineType) {
            case FIWARE:
                responseParseEngine = new FiWareEngine();
                break;
            default:
                responseParseEngine = null;
                break;
        }

        return responseParseEngine;
    }
}
