package de.fhg.fokus.streetlife.mmecp.server.websocket;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.fokus.streetlife.mmecp.server.service.EventInfoServiceImpl;

@javax.websocket.ClientEndpoint
public class PanelEndpoint {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@OnOpen
	public void onOpen(Session session) {
		LOG.info("socket opend");
	}

	@OnMessage
	public void onMessage(String message) {
		LOG.info("Received message: {}", message);
//		EventInfoServiceImpl.newEvent();
	}
}
