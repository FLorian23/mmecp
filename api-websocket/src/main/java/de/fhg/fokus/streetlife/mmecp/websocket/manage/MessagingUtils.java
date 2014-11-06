package de.fhg.fokus.streetlife.mmecp.websocket.manage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.Session;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by bdi on 06/11/14.
 */
@ApplicationScoped
public class MessagingUtils implements Serializable {

    @Inject
    private SessionManager sm;

    public void broadcastMessage(String endpoint, String message) throws SessionManagerException, IOException {
        for (Session session : sm.getEndpointSessions(endpoint)) {
            session.getBasicRemote().sendText(message);
        }
    }

}
