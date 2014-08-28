package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GuidancePopUpPanel extends DecoratedPopupPanel implements
		ClickHandler {

	LonLat position;
	VerticalPanel content = new VerticalPanel();

	public GuidancePopUpPanel(boolean autoHide) {
		super(autoHide);
		
		getElement().setId("guidancePopUpPanel");
		
		Button button = new Button("OK");
		button.addClickHandler(this);

		content.add(new HTML("some guidance options..."));
		content.add(button);
		setWidget(content);
	}

	public void onClick(ClickEvent event) {
		 hide();
	}
}