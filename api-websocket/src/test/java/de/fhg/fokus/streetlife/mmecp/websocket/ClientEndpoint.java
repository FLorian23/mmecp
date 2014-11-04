package de.fhg.fokus.streetlife.mmecp.websocket;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bdi on 03/11/14.
 */

@javax.websocket.ClientEndpoint
public class ClientEndpoint {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @OnOpen
	public void onOpen(Session session) {
		try {
			session.getBasicRemote().sendText("Hello STREETLIFE! (Annotation)");
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

    @OnMessage
    public void onMessage(String message) {
        LOG.info("Received message: {}", message);
    }
}
