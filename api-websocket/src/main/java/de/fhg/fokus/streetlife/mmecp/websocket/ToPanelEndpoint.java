package de.fhg.fokus.streetlife.mmecp.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.io.IOUtils;
import org.glassfish.grizzly.utils.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bdi on 03/11/14.
 */
@ServerEndpoint(value = "/objects")
public class ToPanelEndpoint {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	private static List<Session> connectedSessions = new LinkedList<>();

	@OnOpen
	public void onOpen(Session session) {
		LOG.info("User {} connected...", session.getId());
		connectedSessions.add(session);
        InputStream in = this.getClass().getResourceAsStream("/json/example1.json");
        StringWriter writer = new StringWriter();

        try {
            IOUtils.copy(in, writer);
			session.getBasicRemote().sendText("Welcome, this is your sessionId: " + session.getId());
            session.getBasicRemote().sendText(writer.toString());
            LOG.info("Sending JSON to session {} \n {}", session.getId(), writer.toString());
		} catch (IOException e) {
			LOG.error("onOpen sessionId {}", session.getId(), e);
		}
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		broadcastToAll("user " + session.getId() + " says > " + message);
		LOG.info("User {} says: {}", session.getId(), message);
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		connectedSessions.remove(session);
		broadcastToAll("user" + session.getId() + " closed connection...");
		LOG.info("Connection to user {} closed...", session.getId());
	}

	@OnError
	public void onError(Session session, Throwable t) {
		LOG.error("Something went wrong in session [{}]", session.getId(), t);
        LOG.error("Trying to close session {}", session.getId());
        try {
            session.close();
        } catch (IOException e) {
            LOG.error("Can not close session [{}]", session.getId(), e);
        }
	}

	private void broadcastToAll(final String s) {
		for (Session session : connectedSessions) {
			try {
				if (session.isOpen()) {
					session.getBasicRemote().sendText(s);
				}
			} catch (IOException e) {
				LOG.error("Can not send message to session [{}]", session.getId(), e);
			}
		}
	}
}
