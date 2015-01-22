package de.fhg.fokus.streetlife.mmecp.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;

import java.util.ArrayList;

public interface JSONObjectServiceAsync {
	// void getEventInfo(AsyncCallback<EventInfo> callback);
	void getPanelObject(String jSONExample,
			AsyncCallback<PanelObject[]> callback);

	void  getPanelObjectByCoordinate(ArrayList<PanelObject> list, AsyncCallback<PanelObject> callback);
}
