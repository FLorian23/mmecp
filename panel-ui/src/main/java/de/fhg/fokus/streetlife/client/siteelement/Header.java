package de.fhg.fokus.streetlife.client.siteelement;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.client.DAO;
import de.fhg.fokus.streetlife.client.DAO.CITY;
import de.fhg.fokus.streetlife.client.Res;

public class Header extends SiteElement {

	
	public Header() {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.getElement().setId("headerHPanel");
		hPanel.setVerticalAlignment(ALIGN_BOTTOM);
		add(hPanel);

		ImageResource streetlifeImage = Res.INSTANCE.streetlifeImage();
		Image image = new Image(streetlifeImage.getSafeUri());
		image.getElement().setId("streetlifeImg");
		image.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				ContentController.getInstance().show(ContentController.Kind.MAP);
			}
		});
		hPanel.add(image);
		hPanel.add(createButtonForCurrentLocation());
		hPanel.add(createSwitchButton());
		hPanel.add(createListBox());
		Hyperlink hyperlink = new Hyperlink("imprint", "");
		hyperlink.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				ContentController.getInstance().show(ContentController.Kind.IMPRESSUM);
			}
		});
		hPanel.add(hyperlink);
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
		button.getElement().setId("SwitchMapButton");
		return button;
	}

	public Button createButtonForCurrentLocation() {
		Button button = new Button("Locate Me",
				MapContainer.getHandler());
		button.getElement().setId("CurrentLocationButton");
		return button;
	}
}
