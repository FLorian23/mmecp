package de.fhg.fokus.streetlife.mmecp.client.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gwt.core.client.JsonUtils;
import de.fhg.fokus.streetlife.mmecp.client.model.MapContent;
import de.fhg.fokus.streetlife.mmecp.server.json.JSONProcessor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.IOException;

/**
 * Created by bdi on 07/11/14.
 */
@ClientEndpoint
public class WebSocketClient {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Inject
    private MapContent mapContent;

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("Hello MMECP, I'm the PanelUi!");
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @OnMessage
    public void onMessage(String message) {

        if (JsonUtils.safeToEval(message)) {
            mapContent.setObjectJson(message);
            LOG.info("Received new map objects");
        } else {
            LOG.info("Received message: {}", message);

        }
    }
}
