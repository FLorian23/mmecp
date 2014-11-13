package de.fhg.fokus.streetlife.mmecp.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.fhg.fokus.streetlife.mmecp.share.dto.MapObject;

@RemoteServiceRelativePath("EventInfoService")
public interface EventInfoService extends RemoteService {
	public MapObject[] getJSONObject(String jSONExample);
}
