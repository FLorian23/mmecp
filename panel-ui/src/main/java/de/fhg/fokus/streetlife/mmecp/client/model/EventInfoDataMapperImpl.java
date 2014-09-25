package de.fhg.fokus.streetlife.mmecp.client.model;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EventInfoDataMapperImpl implements IEventInfoDataMapper {

	Label caption;
	Label description;
	Label dateTime;
	Label location;
	IsWidget[] widgets;

	public Label getCaption() {
		return caption;
	}

	public Label getDescription() {
		return description;
	}

	public Label getDateTime() {
		return dateTime;
	}

	public Label getLocation() {
		return location;
	}

	public IsWidget[] getOtherElements() {
		return widgets;
	}

	public IsWidget[] getWidgets() {
		return widgets;
	}

	public void setWidgets(IsWidget[] widgets) {
		this.widgets = widgets;
	}

	public void setCaption(Label caption) {
		this.caption = caption;
		this.caption.getElement().setId("eventInfoCaption");
	}

	public void setDescription(Label description) {
		this.description = description;
		this.description.getElement().setId("eventInfoDescription");
	}

	public void setDateTime(Label dateTime) {
		this.dateTime = dateTime;
		this.dateTime.getElement().setId("eventInfoDateTime");
	}

	public void setLocation(Label location) {
		this.location = location;
		this.location.getElement().setId("eventInfoLocation");
	}

	public void fillContent(VerticalPanel content) {
		if (getCaption() != null) content.add(getCaption());
		if (getDescription() != null) content.add(getDescription());
		if (getDateTime() != null) content.add(getDateTime());
		if (getLocation() != null) content.add(getLocation());
		
		IsWidget[] widgets = getOtherElements();
		for (int i = 0; i <= widgets.length; i++) {
			if (widgets[i] != null) content.add(widgets[i]);
		}
	}

}
