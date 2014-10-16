package de.fhg.fokus.streetlife.mmecp.client.controller;

import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.Impressum;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.TabPanelManager;

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
			Impressum.get().getPanel().getElement().getParentElement().setId(Impressum.cssID + "Wrapper");
			break;
		case TABPANEL:
			getVerticalPanel().add(TabPanelManager.get());
			TabPanelManager.get().getElement().getParentElement().setId(TabPanelManager.cssID + "Wrapper");
			break;
		default:
			break;
		}
	}
}
