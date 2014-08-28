package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoService;
import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoServiceAsync;
import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public class EventInfoScheduler extends Timer implements
		AsyncCallback<EventInfo> {

	boolean isWaiting = false;

	@Override
	public void run() {
		if (!isWaiting) {
			EventInfoServiceAsync eventInfoService = GWT
					.create(EventInfoService.class);
			eventInfoService.getEventInfo(this);
			isWaiting = true;
		} else {
		}
	}

	public void onFailure(Throwable caught) {
		// Window.alert("OnFailure-Error: " + caught.getMessage());
		// TODO:
		System.out.println("OnFailure-Error: " + caught.getMessage());
		isWaiting = false;
	}

	public void onSuccess(EventInfo result) {
		isWaiting = false;
		
		if (result.getMessage().compareTo("success") != 0)
			return;
		PopUpPanelContainer.get().newNotification(result, 0);
		
	}
}
