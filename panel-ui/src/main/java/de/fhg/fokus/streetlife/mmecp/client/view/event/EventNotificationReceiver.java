package de.fhg.fokus.streetlife.mmecp.client.view.event;

import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;

public interface EventNotificationReceiver {

	public void newNotification(PanelObject event, int position);
}
