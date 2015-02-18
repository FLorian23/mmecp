package de.fhg.fokus.streetlife.mmecp.client.controller;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;

import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectService;
import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectServiceAsync;
import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject.Type;

public class SocketController {

	private final static SocketController instance = new SocketController();

	public final Websocket socketToBackEnd = new Websocket("ws://193.175.133.251/api-websocket");//"ws://193.175.133.251:8080/api-websocket/panelui"); //193.175.133.251

	private SocketController() {
		openSocketToBackEnd();
	}

	public static SocketController get() {
		return instance;
	}

	private void openSocketToBackEnd() {
		socketToBackEnd.addListener(new WebsocketListener() {
			public void onClose() {
				LOG.logToConsole("Connection to backend closed.");
			}

			public void onMessage(String msg) {
				LOG.logToConsole("New objects to draw from server. Message is:\n" + msg.substring(0, 50) + "...");

				AsyncCallback<PanelObject[]> callback = new AsyncCallback<PanelObject[]>() {
					public void onSuccess(PanelObject[] result) {
						if (result == null){
							LOG.logToConsole("Result is null!");
							return;
						}

						LOG.logToConsole(result.length + " new PanelObjetcs.");
						for (int i = 0; i < result.length; i++) {
							if (result[i].getType().equals(Type.MAPOBJECT)){
								MapContainer.get().drawObject(result[i]);

							}else if(result[i].getType().equals(Type.NOTIFICATION)){
								result[i].setMapObject(MapContainer.get().getMapObjectByID(result[i].getObjectType(), result[i].getObjectID()));
								if (result[i].getMapObject() == null){
									LOG.logToConsole("Mapobject regarding this notification not available!");
									break;
								}
								PopUpPanelContainer.get().newNotification(result[i], 0);
							}
						}
					}

					public void onFailure(Throwable caught) {
						LOG.logToConsole("GWT RPC for JSON parsing failed: " + caught.getMessage());
					}
				};

				JSONObjectServiceAsync eventInfoService = GWT.create(JSONObjectService.class);
				eventInfoService.getPanelObject(msg, callback);
			}

			public void onOpen() {
				LOG.logToConsole("Connection to backend opend.");
			}
		});
		socketToBackEnd.open();
	}

	public void sendMessage(String msg, int timeout) {
		if (socketToBackEnd.getState() == 1) {
			LOG.logToConsole("Send request with: " + msg);
			socketToBackEnd.send(msg);
		} else if (timeout > 0) {
			LOG.logToConsole("Websocket not open while sending request with: " + msg + " Try to reconnect! (" + timeout + ")");
			if (socketToBackEnd.getState() == 2)
				socketToBackEnd.open();
			final String m = msg;
			final int t = timeout;
			Timer timer = new Timer() {
				@Override
				public void run() {
					sendMessage(m, (t - 1));
				}
			};
			timer.schedule(1000);
		}
	}
}
