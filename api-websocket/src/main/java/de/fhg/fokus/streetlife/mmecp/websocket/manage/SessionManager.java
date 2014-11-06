package de.fhg.fokus.streetlife.mmecp.websocket.manage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bdi on 06/11/14.
 */
@ApplicationScoped
public class SessionManager implements Serializable {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	private Map<String, List<Session>> sessionMap;

	@PostConstruct
	public void init() {
		LOG.info("@PostConstruct is working");
		sessionMap = new HashMap<String, List<Session>>();

	}

	public List<Session> getEndpointSessions(String endpoint) throws SessionManagerException {
		List<Session> sessions;

		if (sessionMap.get(endpoint) != null) {
			sessions = sessionMap.get(endpoint);
		} else {
			throw new SessionManagerException("Endpoint " + endpoint + " does not exist.");
		}

		return sessions;
	}

    /**
     * Created a new endpoint with an empty list for sessions. Overrides existing endpoint.
     *
     * @param endpoint The endpoint to create
     */
    public void addEndpoint(String endpoint) {
        sessionMap.put(endpoint, new ArrayList<Session>());
    }

    /**
     * Adds a session to an endpoint. Creates an endpoint with an empty list if the endpoint not exist.
     *
     * @param endpoint The endpoint to create
     * @param session The session to create
     * @throws SessionManagerException Thrown if session already exists
     */
	public void addEndpointSession(String endpoint, Session session) throws SessionManagerException {
		if (sessionMap.get(endpoint) == null) {
			this.addEndpoint(endpoint);
		}

		if (!sessionMap.get(endpoint).contains(session)) {
			sessionMap.get(endpoint).add(session);
			LOG.info("Added Session {} to endpoint {}", session.getId(), endpoint);
		} else {
			throw new SessionManagerException("Session " + session.getId() + " already exists in endpoint " + endpoint);
		}
	}

	public void removeSession(String endpoint, Session session) {
		sessionMap.get(endpoint).remove(session);
	}

}
