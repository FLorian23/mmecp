package de.fhg.fokus.streetlife.mmecp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public interface EventInfoServiceAsync {
	void getEventInfo(AsyncCallback<EventInfo> callback);
}
