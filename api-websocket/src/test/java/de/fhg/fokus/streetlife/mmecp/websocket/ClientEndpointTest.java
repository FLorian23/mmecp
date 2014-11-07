package de.fhg.fokus.streetlife.mmecp.websocket;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 * Created by bdi on 03/11/14.
 */

@javax.websocket.ClientEndpoint
public class ClientEndpointTest {

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
    public void onMessage(Session session, String message) throws IOException {
        LOG.info("Received message: {}", message);
    }
}
