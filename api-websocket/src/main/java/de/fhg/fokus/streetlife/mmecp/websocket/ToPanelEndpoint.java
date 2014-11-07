package de.fhg.fokus.streetlife.mmecp.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import de.fhg.fokus.streetlife.configurator.MMECPConfig;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.MessagingUtils;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.SessionManager;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.SessionManagerException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by bdi on 03/11/14.
 */
@ServerEndpoint(value = "/panelui")
public class ToPanelEndpoint {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Inject
	private SessionManager sm;
	@Inject
	private MessagingUtils mu;

	@Inject
	@MMECPConfig(value = "mmecp.backend.api.websocket.endpoint.panelui")
	private String endpointName;

	@OnOpen
	public void onOpen(Session session) throws SessionManagerException, IOException {
		LOG.info("User {} connected...", session.getId());

		sm.addEndpointSession(endpointName, session);
		mu.broadcastMessage(endpointName, "WELCOME to " + endpointName);

		InputStream in = this.getClass().getResourceAsStream("/json/example1.json");
		StringWriter writer = new StringWriter();

		IOUtils.copy(in, writer);
		mu.broadcastMessage(endpointName, writer.toString());
		LOG.info("Sending JSON to session {} \n {}", session.getId(), writer.toString());
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, SessionManagerException {
		mu.broadcastMessage(endpointName, "user " + session.getId() + " says > " + message);
		LOG.info("User {} says: {}", session.getId(), message);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException, SessionManagerException {
		mu.broadcastMessage(endpointName, "user" + session.getId() + " closed connection...");
		sm.removeSession(endpointName, session);
		LOG.info("Connection to user {} closed...", session.getId());
	}

	@OnError
	public void onError(Session session, Throwable t) {
		LOG.error("Something went wrong in session [{}]", session.getId(), t);
		LOG.error("Trying to close session {}", session.getId());
		try {
			session.close();
			LOG.error("Session {} closed", session.getId());
		} catch (IOException e) {
			LOG.error("Can not close session [{}]", session.getId(), e);
		}
	}
}
