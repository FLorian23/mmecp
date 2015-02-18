package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.left;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.*;

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
		//content.addWidgetToPanel(createButtonForCurrentLocation(),
		//		"CurrentLocationButton", "btn btn-primary");
		content.addWidgetToPanel(createSwitchButton(), "SwitchMapButton",
				"btn btn-primary");
		content.addWidgetToPanel(createListBox(), "cityListBox", "form-control");

		//add checkboxes for control the visibility of the several layers
		final CheckBox cbMacrozones = new CheckBox("Macrozones");
		final CheckBox cbMicrozones = new CheckBox("Microzones");

		final CheckBox cbFree = new CheckBox("for free ");
		final CheckBox cbFee = new CheckBox("with Cardblock Clock");
		final CheckBox cbClock = new CheckBox("with fee");

		cbMacrozones.getElement().setClassName("checkBoxMacro");
		cbMicrozones.getElement().setClassName("checkBoxMacro");
		cbFree.getElement().setClassName("checkBoxMicro");
		cbFee.getElement().setClassName("checkBoxMicro");
		cbClock.getElement().setClassName("checkBoxMicro");

		cbMacrozones.setValue(false);
		cbMicrozones.setValue(true);
		cbFree.setValue(false);
		cbFee.setValue(true);
		cbClock.setValue(true);

		cbMacrozones.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()){
					MapContainer.get().visibleLayer(DAO.PARKING.MACRO, true);
				}else{
					MapContainer.get().visibleLayer(DAO.PARKING.MACRO, false);
				}
			}
		});

		cbMicrozones.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()){
					if (!cbFree.getValue()) {
						cbFree.setValue(true);
						MapContainer.get().visibleLayer(DAO.PARKING.FREE, true);
					}
					if (!cbFee.getValue()) {
						cbFee.setValue(true);
						MapContainer.get().visibleLayer(DAO.PARKING.FEE, true);
					}
					if (!cbClock.getValue()) {
						cbClock.setValue(true);
						MapContainer.get().visibleLayer(DAO.PARKING.CLOCK, true);
					}
				}else{
					if (cbFree.getValue()) {
						cbFree.setValue(false);
						MapContainer.get().visibleLayer(DAO.PARKING.FREE, false);
					}
					if (cbFee.getValue()) {
						cbFee.setValue(false);
						MapContainer.get().visibleLayer(DAO.PARKING.FEE, false);
					}
					if (cbClock.getValue()) {
						cbClock.setValue(false);
						MapContainer.get().visibleLayer(DAO.PARKING.CLOCK, false);
					}
				}
			}
		});

		cbFree.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()){
					if (!cbMicrozones.getValue()) cbMicrozones.setValue(true);
					MapContainer.get().visibleLayer(DAO.PARKING.FREE, true);
				}else{
					MapContainer.get().visibleLayer(DAO.PARKING.FREE, false);
				}
			}
		});
		cbFee.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()) {
					if (!cbMicrozones.getValue())
						cbMicrozones.setValue(true);
					MapContainer.get().visibleLayer(DAO.PARKING.FEE, true);
				} else {
					MapContainer.get().visibleLayer(DAO.PARKING.FEE, false);
				}
			}
		});
		cbClock.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				if (event.getValue()){
					if (!cbMicrozones.getValue()) cbMicrozones.setValue(true);
					MapContainer.get().visibleLayer(DAO.PARKING.CLOCK, true);
				}else{
					MapContainer.get().visibleLayer(DAO.PARKING.CLOCK, false);
				}
			}
		});
		MapContainer.get().visibleLayer(DAO.PARKING.FEE, true);
		MapContainer.get().visibleLayer(DAO.PARKING.CLOCK, true);

		content.addWidgetToPanel(cbMacrozones, null, "form-control");
		content.addWidgetToPanel(cbMicrozones, null, "form-control");

		content.addWidgetToPanel(cbFree, null, "form-control");
		content.addWidgetToPanel(cbFee, null, "form-control");
		content.addWidgetToPanel(cbClock, null, "form-control");
	}

	private ListBox createListBox() {
		ListBox lb = new ListBox(false);
		CITY[] values = DAO.CITY.values();
		int indexOfDefaultCity = 0;
		for (int i = 0; i < values.length; i++) {
			if (CITY.ROVERETO.equals(values[i])) indexOfDefaultCity = i;
			lb.addItem(values[i].toString());
		}
		lb.setSelectedIndex(indexOfDefaultCity);
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
