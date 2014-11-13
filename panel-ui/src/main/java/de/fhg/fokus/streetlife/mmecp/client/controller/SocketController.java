package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.util.logging.Level;

import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;

public class SocketController {

	private final static SocketController instance = new SocketController();

	private final Websocket socketToBackEnd = new Websocket(
			"ws://localhost:8080/api-websocket/panelui");

	private SocketController() {
		openSocketToBackEnd();
	}

	public static SocketController get() {
		return instance;
	}

	int h = 0;

	private void openSocketToBackEnd() {

		socketToBackEnd.addListener(new WebsocketListener() {

			public void onClose() {
				LOG.getLogger().log(Level.WARNING, "connection closed...");
			}

			public void onMessage(String msg) {
				// if (JsonUtils.safeToEval(msg)) {
				// LOG.log(Level.WARNING, "Received: " + msg);
				// }
				// LOG.log(Level.WARNING, "onMessage constructor test!");
				LOG.getLogger().log(Level.WARNING, "msg from server: " + msg);
				if (++h != 10)
					socketToBackEnd.send("getObjectsOfType");
				else {
					socketToBackEnd.close();
				}
			}

			public void onOpen() {
				socketToBackEnd.send("getObjectsOfType");
			}
		});

		socketToBackEnd.open();
		LOG.getLogger().log(Level.WARNING,
				"socket.state(): " + socketToBackEnd.getState());
	}
}
