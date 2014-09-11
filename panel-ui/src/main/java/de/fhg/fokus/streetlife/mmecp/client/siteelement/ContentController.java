package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right.SlideBarRight;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.statistic.MathPanel;

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
		lp.getElement().setId("maplayoutpanel");

		lp.add(MapContainer.getInstance());
		lp.add(PopUpPanelContainer.get());
		lp.add(SlideBarRight.get());
		lp.getWidgetContainerElement(PopUpPanelContainer.get()).setId(
				"wrapperPopupPanel");
		lp.getWidgetContainerElement(SlideBarRight.get()).setId(
				"wrapperRightMenu");

		lp.getWidgetContainerElement(MapContainer.getInstance()).setId(
				"wrapperMap");
		tabPanel.add(lp, "Map");
		tabPanel.add(MathPanel.get(), "statistics");
//		tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
//			
//			public void onSelection(SelectionEvent<Integer> event) {
//				if (event.getSelectedItem() == 0){
//					RootPanel.get().getElement().getStyle().setOverflowY(Overflow.HIDDEN);
//				}else if (event.getSelectedItem() == 1) {
//					RootPanel.get().getElement().getStyle().setOverflowY(Overflow.SCROLL);
//				}
//			}
//		});
		MathPanel.get().getElement().getParentElement().setId("MathPanelParent");
		MathPanel.get().getElement().getParentElement().getStyle().setHeight(Window.getClientHeight() -90, Unit.PX);
//		MathPanel.get().getElement().getParentElement().getStyle().setOverflow(Overflow.SCROLL);

		getVerticalPanel().add(tabPanel);
		getVerticalPanel().setCellWidth(tabPanel, "100%");
		getVerticalPanel().setCellHeight(tabPanel, "100%");
		SlideBarRight.get().hide();

		tabPanel.selectTab(0);
	}
}
