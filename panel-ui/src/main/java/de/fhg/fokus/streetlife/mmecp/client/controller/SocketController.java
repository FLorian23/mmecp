package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.util.logging.Level;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;

import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoService;
import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoServiceAsync;
import de.fhg.fokus.streetlife.mmecp.share.dto.MapObject;

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
				LOG.getLogger().log(Level.WARNING, "msg from server: " + msg);

				AsyncCallback<MapObject[]> callback = new AsyncCallback<MapObject[]>() {

					public void onSuccess(MapObject[] result) {
						LOG._log("result is success!!");
						LOG._log("size: " + result.length);
						LOG._log("id: " + result[0].getObjectID());
					}

					public void onFailure(Throwable caught) {
						LOG._log("FAIL!!");
					}
				};

				EventInfoServiceAsync eventInfoService = GWT
						.create(EventInfoService.class);
				eventInfoService.getJSONObject(msg, callback);
			}

			public void onOpen() {
				socketToBackEnd.send("getObjectsOfType:ParkingStations");
			}
		});

		socketToBackEnd.open();
		LOG.getLogger().log(Level.WARNING,
				"socket.state(): " + socketToBackEnd.getState());
	}
}
