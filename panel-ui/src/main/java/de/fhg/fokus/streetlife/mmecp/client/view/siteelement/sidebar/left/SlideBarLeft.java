package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.left;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.Res;
import de.fhg.fokus.streetlife.mmecp.client.model.DAO;
import de.fhg.fokus.streetlife.mmecp.client.model.DAO.CITY;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;

public class SlideBarLeft extends SlideBar {

	private static SlideBarLeft slideBarLeft = null;

	public static SlideBarLeft get() {
		if (slideBarLeft == null) {
			slideBarLeft = new SlideBarLeft();
		}
		return slideBarLeft;
	}

	public SlideBarLeft() {
		super("slideLeftPanel", null, new SiteElement<VerticalPanel>(
				new VerticalPanel(), "SlideBarLeftContentPanel", null), false);

		getPicturePanel().addDomHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (getStatus() == STATUS.CLOSE) {
					setStatus(STATUS.OPEN);
				} else {
					setStatus(STATUS.CLOSE);
				}
			}
		}, ClickEvent.getType());

		buildContent();
		setStatus(STATUS.CLOSE);
	}

	protected void open() {
		if (getStatus() == STATUS.OPEN)
			return;
		$("#" + getWrapperID()).animate("width:" + CSSDynamicData.SlideBarLeft_WIDTH_ROLL_OUT, 500);
		$("#" + getID()).animate("margin-left:" + 0, 500);
	}

	protected void close() {
		if (getStatus() == STATUS.CLOSE)
			return;
		$("#" + getWrapperID()).animate("width:" + CSSDynamicData.SlideBarLeft_WIDTH_ROLL_IN, 500);
		$("#" + getID()).animate(
				"margin-left:" + "-" + (CSSDynamicData.SlideBarLeft_WIDTH_ROLL_OUT - CSSDynamicData.SlideBarLeft_WIDTH_ROLL_IN), 500);
	}

	private void buildContent() {
		content.addWidgetToPanel(createButtonForCurrentLocation(),
				"CurrentLocationButton", "btn btn-primary");
		content.addWidgetToPanel(createSwitchButton(), "SwitchMapButton",
				"btn btn-primary");
		content.addWidgetToPanel(createListBox(), "cityListBox", "form-control");

		//add checkboxes for control the visibility of the several layers
	}

	private ListBox createListBox() {
		ListBox lb = new ListBox(false);
		CITY[] values = DAO.CITY.values();
		for (int i = 0; i < values.length; i++) {
			lb.addItem(values[i].toString());
		}
		lb.addChangeHandler(MapContainer.getChangeHandler());
		lb.addClickHandler(MapContainer.getHandler());
		return lb;
	}

	public Button createSwitchButton() {
		Button button = new Button("Switch Map", MapContainer.getHandler());
		return button;
	}

	public Button createButtonForCurrentLocation() {
		Button button = new Button("Locate Me", MapContainer.getHandler());
		return button;
	}
}
