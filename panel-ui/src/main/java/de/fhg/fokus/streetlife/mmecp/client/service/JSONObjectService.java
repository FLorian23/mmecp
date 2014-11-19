package de.fhg.fokus.streetlife.mmecp.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.fhg.fokus.streetlife.mmecp.share.dto.Notification;
import de.fhg.fokus.streetlife.mmecp.share.dto.MapObject;

@RemoteServiceRelativePath("JSONObject")
public interface JSONObjectService extends RemoteService {
	public MapObject[] getMapObject(String jSONExample);

	public Notification getNotificationObject(String jSONExample);
}
