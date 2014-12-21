package de.fhg.fokus.streetlife.mmecp.dataaggregator.model;

import org.codehaus.jackson.JsonNode;

import java.io.Serializable;

/**
 * Created by bdi on 21/12/14.
 */
public interface ResponseParseEngine extends Serializable {
    public JsonNode parseResponse(String response);
}
