package de.fhg.fokus.streetlife.mmecp.client.model;

import com.fasterxml.jackson.databind.JsonNode;

import javax.enterprise.context.ApplicationScoped;

/**
 * This class is just to test the websocket connection to the server...
 *
 * Created by bdi on 07/11/14.
 */
@ApplicationScoped
public class MapContent {

    private String objectJson;

    public String getObjectJson() {
        return objectJson;
    }

    public void setObjectJson(String objectJson) {
        this.objectJson = objectJson;
    }
}
