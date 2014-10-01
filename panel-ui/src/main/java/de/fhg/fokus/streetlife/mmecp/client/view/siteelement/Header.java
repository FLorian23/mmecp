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
import com.google.gwt.user.client.ui.Widget;

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

		Hyperlink hyperlink = new Hyperlink("imprint", "");
		hyperlink.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				ContentController.getInstance().show(
						ContentController.Kind.IMPRESSUM);
			}
		});
		addWidgetToPanel(hyperlink, "hyperlinkImprint", "");
	}
}
