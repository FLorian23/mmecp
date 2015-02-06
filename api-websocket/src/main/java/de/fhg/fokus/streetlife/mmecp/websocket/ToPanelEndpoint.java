package de.fhg.fokus.streetlife.mmecp.websocket;

import de.fhg.fokus.streetlife.mmecp.configurator.MMECPConfig;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.generic.engine.ResponseParseEngineMethod;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.EngineType;
import de.fhg.fokus.streetlife.mmecp.dataaggregator.model.ResponseParseEngine;
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
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

	@Inject
	@ResponseParseEngineMethod(EngineType.ROVDEMO)
	private ResponseParseEngine rovdemoEngine;
	//@ResponseParseEngineMethod(EngineType.FIWARE)
	//private ResponseParseEngine fiwareEngine;

	@OnOpen
	public void onOpen(Session session) throws IOException, SessionManagerException {
		LOG.info("User {} connected...", session.getId());
		sm.addEndpointSession(endpointName, session);
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, SessionManagerException {
		LOG.info("User {} says: {}", session.getId(), message);
		if (message.startsWith("getObjectsOfType")) {
			String response = getObjectsOfType(message.replace("getObjectsOfType:", ""));
			mu.broadcastMessage(endpointName, rovdemoEngine.parseResponse(response).toString());
		} else if (message.startsWith("newGuidance")) {
			setNewGuidance(message.replace("newGuidance:", ""));
		} else if (message.startsWith("demo")) {

			/*
			//DataAggregator integration example
			try {
				fiwareEngine.parseResponse("This is just a not working example...");
			} catch (Exception e) {
				LOG.debug("Not working example...");
			}

			// simulate notification
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

			List<String> demoObjectBorder = new ArrayList<>();
			writer = new StringWriter();
			IOUtils.copy(this.getClass().getResourceAsStream("/json/borderExample6.json"), writer);
			demoObjectBorder.add(writer.toString());

			SCHEDULED_EXECUTOR_SERVICE.schedule(() -> {
				LOG.info("Sending new border object to {}", endpointName);
				try {
					mu.broadcastMessage(endpointName, demoObjectBorder.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, TWENTY_SECONDS+TWENTY_SECONDS, TimeUnit.MILLISECONDS);
			*/

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
		LOG.info("Get objects of type: {}", type);
		// example data
		//TODO replace example data with real data
		if (type.equals("ParkingStationsClock")) {
			StringWriter writer = new StringWriter();
			try {
				IOUtils.copy(this.getClass().getResourceAsStream("/json/parkingStationsClock.json"), writer);
			} catch (IOException e) {
				LOG.error("Can't read resource!", e);
			}
			return writer.toString();
		}
		if (type.equals("ParkingStationsFee")) {
			StringWriter writer = new StringWriter();
			try {
				IOUtils.copy(this.getClass().getResourceAsStream("/json/parkingStationsFee.json"), writer);
			} catch (IOException e) {
				LOG.error("Can't read resource!", e);
			}
			return writer.toString();
		}
		if (type.equals("ParkingStationsFree")) {
			StringWriter writer = new StringWriter();
			try {
				IOUtils.copy(this.getClass().getResourceAsStream("/json/parkingStationsFree.json"), writer);
			} catch (IOException e) {
				LOG.error("Can't read resource!", e);
			}
			return writer.toString();
		}
		if (type.equals("ParkingAreas")) {
			StringWriter writer = new StringWriter();
			try {
				IOUtils.copy(this.getClass().getResourceAsStream("/json/parkingStationsMacro.json"), writer);
			} catch (IOException e) {
				LOG.error("Can't read resource!", e);
			}
			return writer.toString();
		}
		return null;
	}

	private void setNewGuidance(String guidance) {
		//TODO generate guidance and save to data storage
	}
}
