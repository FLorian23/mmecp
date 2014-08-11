package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public interface IEventInfoView {

	Label getCaption();

	Label getDescription();

	Label getDateTime();

	Label getLocation();

	IsWidget[] getOtherElements();
	
	void fillContent(VerticalPanel vp);
}
