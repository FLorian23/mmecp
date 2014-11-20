package de.fhg.fokus.streetlife.mmecp.client.view.event;

import static com.google.gwt.query.client.GQuery.$;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.query.client.css.TextAlignProperty.TextAlign;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.model.IEventInfoDataMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar.STATUS;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.DtoToGWTElementMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.share.dto.PanelObject;

public class PopUpPanel extends SiteElement<SimplePanel> implements
		ClickHandler {

	private PanelObject panelObject = null;
	private static int id = 0;

	public PopUpPanel(PanelObject panelObject) {
		super(new SimplePanel(), "eventinfo_" + id++,
				"eventinfonotification alert-box notice");
		this.setPanelObject(panelObject);
		getPanel().setSize(
				CSSDynamicData.PopUpPanelContainer_WIDTH_ROLL_IN + "px",
				CSSDynamicData.PopUpPanelContainer_HEIGHT + "px");

		$("#" + getPanel().getElement().getId()).css(
				CSS.TEXT_ALIGN.with(TextAlign.LEFT));
		getPanel().addDomHandler(this, ClickEvent.getType());
		getPanel().add(new Label(panelObject.getDescription() + ""));
	}

	public PanelObject getPanelObject() {
		return panelObject;
	}

	public void setPanelObject(PanelObject panelObject) {
		this.panelObject = panelObject;
	}

	public void onClick(ClickEvent event) {
		PopUpPanelContainer.get().hidePanelObject(this);
		// Show futher information in right sidebar
		VerticalPanel content = SlideBarRight.get().getContent();
		content.clear();
		// TODO: mapObject Inhalt nutzen für das seitliche Panel und zoom dort
		// hin!
		IEventInfoDataMapper eventInfo = DtoToGWTElementMapper
				.map(getPanelObject().getMapObject());
		eventInfo.fillContent(content);
		SlideBarRight.get().setStatus(STATUS.OPEN);

		// Zoom
		/*
		 * GEO Location(1): 1228781.1226039/5762482.0842204 
		 * GEO Location(2):
		 * 11.038328634262818/45.888392762760496
		 */
		// TODO: Coords anpassen
		List<Double> coordinates = getPanelObject().getMapObject().getLocation()
				.getCoordinates();
		MapContainer.switchLocation(coordinates.get(0), coordinates.get(1), 16);
	}

	public void show() {
		$("#" + getPanel().getElement().getId()).animate(
				"width:" + CSSDynamicData.PopUpPanelContainer_WIDTH_ROLL_OUT,
				200);
	}

	public void hide() {
		$("#" + getPanel().getElement().getId()).animate(
				"width:" + CSSDynamicData.PopUpPanelContainer_WIDTH_ROLL_IN,
				200);
	}

	public int getId() {
		return id;
	}
}
