package de.fhg.fokus.streetlife.mmecp.client.controller;

import java.util.List;
import java.util.logging.Level;

import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sksamuel.gwt.websockets.Websocket;
import com.sksamuel.gwt.websockets.WebsocketListener;

import de.fhg.fokus.streetlife.mmecp.client.model.DAO;
import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoService;
import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoServiceAsync;
import de.fhg.fokus.streetlife.mmecp.client.test.ExampleData;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.share.dto.Color;
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

						LOG._log("result.length: " + result.length);
						for (int i_result = 0; i_result < result.length; i_result++) {

							LOG._log("result[i_result].getElements().size(): "
									+ result[i_result].getElements().size());
							for (int i_elements = 0; i_elements < result[i_result]
									.getElements().size(); i_elements++) {
								if (result[i_result].getElements()
										.get(i_elements).getMaparea() != null) {

									List<List<List<Double>>> coordinates = result[i_result]
											.getElements().get(i_elements)
											.getMaparea().getArea()
											.getCoordinates();
									Color color = result[i_result]
											.getElements().get(i_elements)
											.getMaparea().getColor();
									// Coordinaten
									LOG._log("coordinates.get(0).size(): "
											+ coordinates.get(0).size());

									LonLat[] lonLatArray = new LonLat[coordinates
											.get(0).size()];
									for (int i_coordinates = 0; i_coordinates < coordinates
											.get(0).size(); i_coordinates++) {

										// Ein Wertepaar
										Double d1 = coordinates.get(0)
												.get(i_coordinates).get(0);
										Double d2 = coordinates.get(0)
												.get(i_coordinates).get(1);
										LOG._log(d1 + "/" + d2);

										lonLatArray[i_coordinates] = new LonLat(
												d1, d2);
									}
									// String hex =
									// String.format("#%02x%02x%02x",
									// Integer.valueOf((int)color.getRed()),
									// Integer.valueOf((int)color.getGreen()),
									// Integer.valueOf((int)color.getBlue()));
									MapContainer
											.get()
											.drawPolygon(
													lonLatArray,
													true,
													ExampleData
															.getStyleForPolygon(DAO.RED));

								} else {
									LOG._log("result[i_result].getElements().get(i_elements).getMaparea() == NULL == TRUE!!");
								}
							}
						}
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
