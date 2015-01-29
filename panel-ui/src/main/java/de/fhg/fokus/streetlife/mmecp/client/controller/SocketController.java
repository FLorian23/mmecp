package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.util.logging.Level;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.visualization.client.formatters.BarFormat;
import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;

import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectService;
import de.fhg.fokus.streetlife.mmecp.client.service.JSONObjectServiceAsync;
import de.fhg.fokus.streetlife.mmecp.client.test.ExampleData;
import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.share.dto.Color;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject.Type;

public class SocketController {

	private final static SocketController instance = new SocketController();
	public final Websocket socketToBackEnd = new Websocket("ws://193.175.133.251:8080/api-websocket/panelui");
	//
	//http://193.175.133.251:8080/panelUI/
	private SocketController() {
		openSocketToBackEnd();
	}

	public static SocketController get() {
		return instance;
	}

	int counter = 0;
	boolean busy = false;
	private void openSocketToBackEnd() {
		socketToBackEnd.addListener(new WebsocketListener() {
			public void onClose() {
				LOG.getLogger().log(Level.WARNING, "connection closed...");
			}

			public void onMessage(String msg) {
				LOG.getLogger().info("New objects to draw from server");
				//LOG.getLogger().info(msg);
				//If MapObjekt
				//*******************************************
				AsyncCallback<PanelObject[]> callback = new AsyncCallback<PanelObject[]>() {
					public void onSuccess(PanelObject[] result) {
						if (result == null){
							LOG.logToConsole("result is null!");
							return;
						}
						LOG.logToConsole(result.length + " new PanelObjetcs(" + result[0].getObjectSubtype() + ")");


						for (int i = 0; i < result.length; i++) {
							LOG.logToConsole(result[i].getType() + "");
							
							if (result[i].getType().equals(Type.MAPOBJECT)){
								//LOG.logToConsole("New MAPOBJECT");

								//Data.myPanelObjects.add(result[i]);
								//MapContainer.get().addnewMapObject(result[i]);

								MapContainer.get().drawObject(result[i]);

								//TODO:
							}else if(result[i].getType().equals(Type.NOTIFICATION)){
								LOG.logToConsole("New Notification: " + result[i].getDescription());
								result[i].setMapObject(MapContainer.get().getMapObjectByID(result[i].getObjectType(), result[i].getObjectID())); //"ParkingStation", "1"));
								if (result[i].getMapObject() == null){
									LOG.logToConsole("NO MATCH MAPOBJECT");
									break;
								}
								PopUpPanelContainer.get().newNotification(result[i], 0);
							}
						}
					}

					public void onFailure(Throwable caught) {
						LOG._log("GWT RPC for JSON parsing failed");
					}
				};

				JSONObjectServiceAsync eventInfoService = GWT.create(JSONObjectService.class);
				eventInfoService.getPanelObject(msg, callback);
				//*******************************************
				
				
				//If Notification
				//*******************************************
//				Notification notification = new Notification();
//				notification.setId(counter++ + "");
//				notification.setMapObject(MapContainer.get().getMapObjectByID("ParkingStation", 1));
//				PopUpPanelContainer.get().newNotification(notification, 0);
				//*******************************************
			}

			//getObjectsOfType:ParkingAreas => Macro
			public void onOpen() {
				LOG.logToConsole("Send Request getObjectsOfType:ParkingStations");

				socketToBackEnd.send("getObjectsOfType:ParkingStations");



				//LOG.logToConsole("Send Request getObjectsOfType:Macro");
				//socketToBackEnd.send("getObjectsOfType:Macro");
			}
		});

		socketToBackEnd.open();
		LOG.getLogger().log(Level.WARNING,
				"socket.state(): " + socketToBackEnd.getState());
	}
}
