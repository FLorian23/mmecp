package de.fhg.fokus.streetlife.mmecp.share.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Notification implements IsSerializable {

	private MapObject mapObject;
	private String notificationText;
	private String id;

	public MapObject getMapObject() {
		return mapObject;
	}

	public void setMapObject(MapObject mapObject) {
		this.mapObject = mapObject;
	}

	public String getNotificationText() {
		return notificationText;
	}

	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
