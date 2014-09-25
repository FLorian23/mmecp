package de.fhg.fokus.streetlife.mmecp.client.view.siteelement;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;

import de.fhg.fokus.streetlife.mmecp.client.Res;
import de.fhg.fokus.streetlife.mmecp.client.controller.ContentController;
import de.fhg.fokus.streetlife.mmecp.client.model.DAO;
import de.fhg.fokus.streetlife.mmecp.client.model.DAO.CITY;

public class Header extends SiteElement<HorizontalPanel> {

	public Header() {
		super(new HorizontalPanel(), "headerHPanel", "");
		HorizontalPanel hPanel = getPanel();
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);

		ImageResource streetlifeImage = Res.INSTANCE.streetlifeImage();
		Image image = new Image(streetlifeImage.getSafeUri());
		image.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				ContentController.getInstance().show(
						ContentController.Kind.TABPANEL);
			}
		});
		addWidgetToPanel(image, "streetlifeImg", "");
		addWidgetToPanel(createButtonForCurrentLocation(), "CurrentLocationButton",
				"btn btn-primary");
		addWidgetToPanel(createSwitchButton(), "SwitchMapButton", "btn btn-primary");
		addWidgetToPanel(createListBox(), "cityListBox", "form-control");

		Hyperlink hyperlink = new Hyperlink("imprint", "");
		hyperlink.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				ContentController.getInstance().show(
						ContentController.Kind.IMPRESSUM);
			}
		});
		addWidgetToPanel(hyperlink, "hyperlinkImprint", "");
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
