package de.fhg.fokus.streetlife.mmecp.client.view.siteelement;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.controller.EventInfoScheduler;
import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.left.SlideBarLeft;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.statistic.MathPanel;

public class TabPanelManager extends SiteElement<TabPanel> {

	private static TabPanelManager instance = null;

	private TabPanelManager() {
		super(new TabPanel(), "tabpanel", null);
		buildMapArchitecture();
	}

	public static TabPanelManager get() {
		if (instance == null)
			return new TabPanelManager();
		return instance;
	}

	public void buildMapArchitecture() {
		getPanel().setVisible(true);

		LayoutPanel lp = new LayoutPanel();
		lp.getElement().setId("maplayoutpanel");

		MapContainer.get().setParentPanel(lp);
		PopUpPanelContainer.get().setParentPanel(lp);
		SlideBarLeft.get().setParentPanel(lp);
		SlideBarRight.get().setParentPanel(lp);

		Timer t = new EventInfoScheduler(PopUpPanelContainer.get());
		t.scheduleRepeating(5000);

		// Fill Tabpanel
		addWidgetToTabPanel(lp, "Map", "maplayoutpanel", null);
		addWidgetToTabPanel(MathPanel.get().getPanel(), "Statistik",
				"statistics", null);

		MathPanel.get().getPanel().getElement().getParentElement().getStyle()
				.setHeight(Window.getClientHeight() - 90, Unit.PX);
		getPanel().selectTab(0);
	}
}

// tabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
//
// public void onSelection(SelectionEvent<Integer> event) {
// if (event.getSelectedItem() == 0){
// RootPanel.get().getElement().getStyle().setOverflowY(Overflow.HIDDEN);
// }else if (event.getSelectedItem() == 1) {
// RootPanel.get().getElement().getStyle().setOverflowY(Overflow.SCROLL);
// }
// }
// });
