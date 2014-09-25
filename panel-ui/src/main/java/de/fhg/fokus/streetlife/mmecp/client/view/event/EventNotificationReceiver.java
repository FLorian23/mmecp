package de.fhg.fokus.streetlife.mmecp.client.view.event;

import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public interface EventNotificationReceiver {

	public void newNotification(EventInfo event, int position);
}
