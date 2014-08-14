package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SiteElement extends VerticalPanel {

	public void addWidget(Widget w, String cssID){
		w.getElement().setId(cssID);
	}
}
