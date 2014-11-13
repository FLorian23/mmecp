package de.fhg.fokus.streetlife.mmecp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.fhg.fokus.streetlife.mmecp.share.dto.MapObject;

public interface EventInfoServiceAsync {
	// void getEventInfo(AsyncCallback<EventInfo> callback);
	void getJSONObject(String jSONExample, AsyncCallback<MapObject[]> callback);
}
