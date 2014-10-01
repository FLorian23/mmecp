package de.fhg.fokus.streetlife.mmecp.client.controller;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.Impressum;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.TabPanelManager;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.math.MathPanel;

public class ContentController {

	public static enum Kind {
		IMPRESSUM, TABPANEL;
	}

	private static ContentController instance = null;
	private VerticalPanel verticalPanel = null;

	public VerticalPanel getVerticalPanel() {
		if (verticalPanel == null) {
			verticalPanel = new VerticalPanel();
			verticalPanel.getElement().setId("contentPanel");
		}
		return verticalPanel;
	}

	public static ContentController getInstance() {
		if (instance == null) {
			instance = new ContentController();
		}
		return instance;
	}

	public void show(Kind k) {
		getVerticalPanel().clear();
		switch (k) {
		case IMPRESSUM:
			getVerticalPanel().add(Impressum.get().getPanel());
			break;
		case TABPANEL:
			getVerticalPanel().add(TabPanelManager.get().getPanel());
			break;
		default:
			break;
		}
	}
}
