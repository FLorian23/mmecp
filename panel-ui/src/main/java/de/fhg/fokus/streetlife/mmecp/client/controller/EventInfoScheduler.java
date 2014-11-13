package de.fhg.fokus.streetlife.mmecp.client.controller;

import de.fhg.fokus.streetlife.mmecp.client.view.event.EventNotificationReceiver;

public class EventInfoScheduler {

	boolean isWaiting = false;

	EventNotificationReceiver receiver = null;

	public EventInfoScheduler(EventNotificationReceiver receiver) {
		this.receiver = receiver;
	}

	// @Override
	// public void run() {
	// if (!isWaiting) {
	// EventInfoServiceAsync eventInfoService = GWT
	// .create(EventInfoService.class);
	// eventInfoService.getEventInfo(this);
	// isWaiting = true;
	// } else {
	// }
	// }

	// public void onFailure(Throwable caught) {
	// // TODO:
	// System.out.println("OnFailure-Error: " + caught.getMessage());
	// isWaiting = false;
	// }
	//
	// public void onSuccess(EventInfo result) {
	// isWaiting = false;
	//
	// if (result.getMessage().compareTo("success") != 0)
	// return;
	// receiver.newNotification(result, 0);
	//
	// }
}
