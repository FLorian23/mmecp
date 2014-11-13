package de.fhg.fokus.streetlife.mmecp.websocket;

import de.fhg.fokus.streetlife.configurator.MMECPConfig;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.MessagingUtils;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.SessionManager;
import de.fhg.fokus.streetlife.mmecp.websocket.manage.SessionManagerException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by bdi on 03/11/14.
 */
@ServerEndpoint(value = "/panelui")
public class ToPanelEndpoint {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	// for simulation
	private Timer timer = new Timer();
	ArrayList<String> demoObjects = new ArrayList<String>();

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
		
		// simulate notification
		//TODO delete for real stuff
		StringWriter writer = new StringWriter();
		IOUtils.copy(this.getClass().getResourceAsStream("/json/example5.json"), writer);
		demoObjects.add(writer.toString());
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				LOG.info("Sending new object and notification to {}", endpointName);
				try {
					mu.broadcastMessage(endpointName, demoObjects.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 20*1000); // 20 seconds
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, SessionManagerException {
		LOG.info("User {} says: {}", session.getId(), message);
		if (message.startsWith("getObjectsOfType")) {
			mu.broadcastMessage(endpointName, getObjectsOfType(message.replace("getObjectsOfType:", "")));
		} else if (message.startsWith("newGuidance")) {
			setNewGuidance(message.replace("newGuidance:", ""));
		} else throw new IOException("Can't interpret the message");
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
			session.close();
			LOG.error("Session {} closed", session.getId());
		} catch (IOException e) {
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
					IOUtils.copy(this.getClass().getResourceAsStream("/json/example"+i+".json"), writer);
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
