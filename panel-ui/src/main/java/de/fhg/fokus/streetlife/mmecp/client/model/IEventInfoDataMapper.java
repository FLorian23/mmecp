package de.fhg.fokus.streetlife.mmecp.client.model;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public interface IEventInfoDataMapper {

	Label getCaption();

	Label getDescription();

	Label getDateTime();

	Label getLocation();

	IsWidget[] getOtherElements();
	
	void fillContent(VerticalPanel vp);
}
