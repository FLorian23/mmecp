package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map;

import de.fhg.fokus.streetlife.mmecp.client.model.MapContent;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Pixel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

import javax.inject.Inject;

public class GuidancePopUpPanel extends SiteElement<PopupPanel> implements
		ClickHandler {

	LonLat position;
	VerticalPanel content = new VerticalPanel();

	@Inject
	private MapContent mapContent;

	public GuidancePopUpPanel(boolean autoHide, LonLat p1, LonLat p2) {
		super(new PopupPanel(autoHide), "guidancePopUpPanel", null);

		Button button = new Button("OK");
		button.addClickHandler(this);

		content.add(new HTML("GEO Location(1): " + p1.lon() + "/" + p1.lat()));
		content.add(new HTML("GEO Location(2): " + p2.lon() + "/" + p2.lat()));
		content.add(new HTML("some guidance options..."));
		content.add(new HTML(mapContent.getObjectJson()));
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