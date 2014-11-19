package de.fhg.fokus.streetlife.mmecp.client.view.event;

import de.fhg.fokus.streetlife.mmecp.share.dto.Notification;

public interface EventNotificationReceiver {

	public void newNotification(Notification event, int position);
}
