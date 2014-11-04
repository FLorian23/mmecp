package de.fhg.fokus.streetlife.mmecp.websocket;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

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
        try {
            session.getBasicRemote().sendText("Welcome, this is your sessionId: " + session.getId());
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


    private void broadcastToAll(final String s) {
        for (Session session : connectedSessions) {
            try {
                if (session.isOpen()) {
                    session.getBasicRemote().sendText(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
