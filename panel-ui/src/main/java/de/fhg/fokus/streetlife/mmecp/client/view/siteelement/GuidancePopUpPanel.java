package de.fhg.fokus.streetlife.mmecp.client.view.siteelement;

import org.gwtopenmaps.openlayers.client.LonLat;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GuidancePopUpPanel extends SiteElement<PopupPanel> implements
		ClickHandler {

	LonLat position;
	VerticalPanel content = new VerticalPanel();

	public GuidancePopUpPanel(boolean autoHide) {
		super(new PopupPanel(autoHide), "guidancePopUpPanel", null);

		Button button = new Button("OK");
		button.addClickHandler(this);

		content.add(new HTML("some guidance options..."));
		content.add(button);
		getPanel().setWidget(content);
	}

	public void onClick(ClickEvent event) {
		getPanel().hide();
	}
	
	public Widget asWidget() {
		return getPanel();
	}
}