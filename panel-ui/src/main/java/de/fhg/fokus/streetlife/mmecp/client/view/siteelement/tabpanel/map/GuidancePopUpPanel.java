package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map;

import java.util.logging.Logger;

import org.gwtopenmaps.openlayers.client.LonLat;

import com.github.gwtbootstrap.client.ui.TextArea;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;

public class GuidancePopUpPanel extends SiteElement<PopupPanel> implements
		ClickHandler {

	LonLat position;
	VerticalPanel content = new VerticalPanel();
	private final Logger LOG = Logger.getLogger(GuidancePopUpPanel.class
			.getName());

	public GuidancePopUpPanel(boolean autoHide, LonLat p1, LonLat p2) {
		super(new PopupPanel(autoHide), "guidancePopUpPanel", null);

		Button button = new Button("OK");
		button.getElement().setId("guidancepopupbutton");
		button.getElement().setClassName("btn btn-primary");
		button.addClickHandler(this);

		// content.add(new HTML("GEO Location(1): " + p1.lon() + "/" +
		// p1.lat()));
		// content.add(new HTML("GEO Location(2): " + p2.lon() + "/" +
		// p2.lat()));
		// content.add(new HTML("some guidance options..."));

		/*
		 *  description: alert text  from: timestamp of the start of the
		 * interval in which the alert is valid  to: timestamp of the end of
		 * the interval in which the alert is valid  effect: description of the
		 * update impact on the transport network (e.g., road blocked)  type:
		 * category of the alert, one of DELAY, ACCIDENT, ROAD, STRIKE, PARKING
		 * or CUSTOM.  entity: reference to the social entity ID, if any. 
		 * note: additional info for the alert
		 */

		String htmlForm = "";
		htmlForm += "<dl class=\"dl-horizontal\">";

		// Geo
		htmlForm += "<dt>Location</dt>";
		htmlForm += "<dd>" + Math.round(p2.lon()) + "/" + Math.round(p2.lat())
				+ "</dd>";

		// From
		htmlForm += "<dt>From</dt>";
		htmlForm += "<dd>" + new DateBox().toString() + "</dd>";

		// To
		htmlForm += "<dt>To</dt>";
		htmlForm += "<dd>" + new DateBox().toString() + "</dd>";
		

		// Effect
		htmlForm += "<dt>Effect</dt>";
		htmlForm += "<dd>" + new TextBox().toString() + "</dd>";

		// Type
		htmlForm += "<dt>Type</dt>";
		ListBox lb = new ListBox();
		lb.addItem("DELAY");
		lb.addItem("ACCIDENT");
		lb.addItem("ROAD");
		lb.addItem("STRIKE");
		lb.addItem("PARKING");
		lb.addItem("CUSTOM");
		htmlForm += "<dd>" + lb.toString() + "</dd>";
		
		// Note
		TextArea textArea = new TextArea();
		textArea.setId("guidancetextarea");
		htmlForm += "<dt>Note</dt>";
		htmlForm += "<dd>" + textArea.toString() + "</dd>";
		
		htmlForm += "</dl>";

		content.add(new HTML(htmlForm));
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