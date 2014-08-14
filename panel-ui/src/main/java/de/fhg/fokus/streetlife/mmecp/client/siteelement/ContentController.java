package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TabPanel;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.left.SlideBarLeft;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right.SlideBarRight;

public class ContentController {

	public static enum Kind {
		IMPRESSUM, MAP;
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
			getVerticalPanel().add(Impressum.getInstance());
			break;
		case MAP:
			buildMapArchitecture();
			break;
		default:
			break;
		}
	}

	private void buildMapArchitecture() {
		TabPanel tabPanel = new TabPanel();
		
		tabPanel.setVisible(true);
		tabPanel.getElement().setId("tabpanel");
		LayoutPanel lp = new LayoutPanel();
		lp.getElement().setId("maplayoutpanel");//tabcontainerMap");
		
		lp.add(MapContainer.getInstance());
		lp.add(SlideBarLeft.get());
		lp.add(PopUpPanelContainer.get());
		lp.add(SlideBarRight.get());
		lp.getWidgetContainerElement(PopUpPanelContainer.get()).setId(
				"wrapperPopupPanel");
		lp.getWidgetContainerElement(SlideBarLeft.get()).setId(
				"wrapperLeftMenu");
		lp.getWidgetContainerElement(SlideBarRight.get()).setId(
				"wrapperRightMenu");
		
		
		lp.getWidgetContainerElement(MapContainer.getInstance()).setId("wrapperMap");
		tabPanel.add(lp, "Map");
		tabPanel.add(new Label("some math..."), "statistics");
		
		getVerticalPanel().add(tabPanel);
		getVerticalPanel().setCellWidth(tabPanel, "100%");
		getVerticalPanel().setCellHeight(tabPanel, "100%");
		
		tabPanel.selectTab(0);
		
	}
}
