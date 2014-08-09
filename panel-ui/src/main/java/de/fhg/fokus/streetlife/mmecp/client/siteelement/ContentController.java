package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

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
		LayoutPanel lp = new LayoutPanel();
		lp.getElement().setId("mapContainer");
		lp.add(MapContainer.getInstance());
		lp.add(SlideBarLeft.get());
		lp.add(PopUpPanel.get());
		lp.add(SlideBarRight.get());
		lp.getWidgetContainerElement(PopUpPanel.get()).setId(
				"wrapperPopupPanel");
		lp.getWidgetContainerElement(SlideBarLeft.get()).setId(
				"wrapperLeftMenu");
		lp.getWidgetContainerElement(SlideBarRight.get()).setId(
				"wrapperRightMenu");
		getVerticalPanel().add(lp);
		getVerticalPanel().setCellWidth(lp, "100%");
		getVerticalPanel().setCellHeight(lp, "100%");
	}
}
