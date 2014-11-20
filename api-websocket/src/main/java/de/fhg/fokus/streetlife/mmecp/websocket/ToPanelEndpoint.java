package de.fhg.fokus.streetlife.mmecp.websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fhg.fokus.streetlife.mmecp.configurator.MMECPConfig;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.MessagingUtils;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.SessionManager;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.SessionManagerException;

/**
 * Created by bdi on 03/11/14.
 */
@ServerEndpoint(value = "/panelui")
public class ToPanelEndpoint {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	// for simulation
	private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();
	private final int TWENTY_SECONDS = 20 * 1000;
	List<String> demoObject = new ArrayList<>();
	List<String> demoNotification = new ArrayList<>();

	@Inject
	private SessionManager sm;

	@Inject
	private MessagingUtils mu;

	@Inject
	@MMECPConfig(value = "mmecp.backend.api.websocket.endpoint.panelui")
	private String endpointName;

	@OnOpen
	public void onOpen(Session session) throws IOException, SessionManagerException {
		LOG.info("User {} connected...", session.getId());
		sm.addEndpointSession(endpointName, session);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, SessionManagerException {
		LOG.info("User {} says: {}", session.getId(), message);
		if (message.startsWith("getObjectsOfType")) {
			mu.broadcastMessage(endpointName, getObjectsOfType(message.replace("getObjectsOfType:", "")));
		} else if (message.startsWith("newGuidance")) {
			setNewGuidance(message.replace("newGuidance:", ""));
		} else if (message.startsWith("demo")) {
			// simulate notification
			//TODO delete for real stuff
			StringWriter writer = new StringWriter();
			IOUtils.copy(this.getClass().getResourceAsStream("/json/mapExample5.json"), writer);
			demoObject.add(writer.toString());
			writer = new StringWriter();
			IOUtils.copy(this.getClass().getResourceAsStream("/json/notifyExample5.json"), writer);
			demoNotification.add(writer.toString());

			SCHEDULED_EXECUTOR_SERVICE.schedule(() -> {
				LOG.info("Sending new object and notification to {}", endpointName);
				try {
					mu.broadcastMessage(endpointName, demoObject.toString());
					mu.broadcastMessage(endpointName, demoNotification.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, TWENTY_SECONDS, TimeUnit.MILLISECONDS);
		} else
			throw new IOException("Can't interpret the message");
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) throws IOException, SessionManagerException {
		LOG.info("Connection to user {} closed because: {}", session.getId(), closeReason.toString());
		sm.removeSession(endpointName, session);
	}

	@OnError
	public void onError(Session session, Throwable t) {
		LOG.error("Something went wrong in session [{}]", session.getId(), t);
		LOG.error("Trying to close session {}", session.getId());
		try {
			onClose(session, new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, t.getMessage()));
			LOG.error("Session {} closed", session.getId());
		} catch (IOException | SessionManagerException e) {
			LOG.error("Can not close session [{}]", session.getId(), e);
		}
	}

	private String getObjectsOfType(String type) {
		// example data
		//TODO real stuff
		ArrayList<String> objects = new ArrayList<String>();
		if (type.equals("ParkingStations")) {
			LOG.info("Get objects of type: {}", type);
			for (int i = 1; i <= 4; i++) {
				try {
					StringWriter writer = new StringWriter();
					IOUtils.copy(this.getClass().getResourceAsStream("/json/mapExample" + i + ".json"), writer);
					objects.add(writer.toString());
				} catch (IOException e) {
					LOG.error("Can't read resource!", e);
				}
			}
		}
		LOG.info("Sending {} objects.", objects.size());
		return objects.toString();
	}

	private void setNewGuidance(String guidance) {
		//TODO generate guidance and save to data storage
	}
}
