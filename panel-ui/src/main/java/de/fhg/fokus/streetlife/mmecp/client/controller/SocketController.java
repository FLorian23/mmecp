package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.util.logging.Level;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;

import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectService;
import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectServiceAsync;
import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.share.dto.Notification;
import de.fhg.fokus.streetlife.mmecp.share.dto.MapObject;

public class SocketController {

	private final static SocketController instance = new SocketController();
	private final Websocket socketToBackEnd = new Websocket("ws://localhost:8080/api-websocket/panelui");

	private SocketController() {
		openSocketToBackEnd();
	}

	public static SocketController get() {
		return instance;
	}

	int counter = 0;
	private void openSocketToBackEnd() {
		socketToBackEnd.addListener(new WebsocketListener() {
			public void onClose() {
				LOG.getLogger().log(Level.WARNING, "connection closed...");
			}

			public void onMessage(String msg) {
				LOG.getLogger().info("New objects to draw from server");

				//If MapObjekt
				//*******************************************
				AsyncCallback<MapObject[]> callback = new AsyncCallback<MapObject[]>() {
					public void onSuccess(MapObject[] result) {
						for (int i = 0; i < result.length; i++) {
							MapContainer.get().drawObject(result[i]);
							
							
							//For Testing
							Notification notification = new Notification();
							notification.setId(counter++ + "");
							notification.setNotificationText("Status of parking lot has changed!");
							notification.setMapObject(MapContainer.get().getMapObjectByID("ParkingStation", 1));
							PopUpPanelContainer.get().newNotification(notification, 0);
						}
					}

					public void onFailure(Throwable caught) {
						LOG._log("FAIL!!");
					}
				};

				JSONObjectServiceAsync eventInfoService = GWT.create(JSONObjectService.class);
				eventInfoService.getMapObject(msg, callback);
				//*******************************************
				
				
				//If Notification
				//*******************************************
//				Notification notification = new Notification();
//				notification.setId(counter++ + "");
//				notification.setMapObject(MapContainer.get().getMapObjectByID("ParkingStation", 1));
//				PopUpPanelContainer.get().newNotification(notification, 0);
				//*******************************************
			}

			public void onOpen() {
				socketToBackEnd.send("getObjectsOfType:ParkingStations");
			}
		});

		socketToBackEnd.open();
		LOG.getLogger().log(Level.WARNING,
				"socket.state(): " + socketToBackEnd.getState());
	}

	//TODO only for demonstration, will be deleted later
	public void requestForDemo() {
		socketToBackEnd.send("demo");
	}
}
