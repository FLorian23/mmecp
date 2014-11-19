package de.fhg.fokus.streetlife.mmecp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.fhg.fokus.streetlife.mmecp.share.dto.Notification;
import de.fhg.fokus.streetlife.mmecp.share.dto.MapObject;

public interface JSONObjectServiceAsync {
	// void getEventInfo(AsyncCallback<EventInfo> callback);
	void getMapObject(String jSONExample, AsyncCallback<MapObject[]> callback);
	void getNotificationObject(String jSONExample, AsyncCallback<Notification> callback);
}
