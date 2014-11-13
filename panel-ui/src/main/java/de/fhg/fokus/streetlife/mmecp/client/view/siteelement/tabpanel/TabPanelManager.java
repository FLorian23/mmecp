package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;

import de.fhg.fokus.streetlife.mmecp.client.controller.EventInfoScheduler;
import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.left.SlideBarLeft;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.map.MapContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.tabpanel.math.MathPanel;
import de.fhg.fokus.streetlife.mmecp.client.view.ui.Example;

public class TabPanelManager extends TabPanel  {

	private static TabPanelManager instance = null;
	public static final String cssID = "tabpanel";
	public static final String mapLayoutPanelcssID = "maplayoutpanel";

	private TabPanelManager() {
		this.getElement().setId(cssID);
		buildMapArchitecture();
	}

	public static TabPanelManager get() {
		if (instance == null)
			instance = new TabPanelManager();
		return instance;
	}

	public void buildMapArchitecture() {
		setVisible(true);

		LayoutPanel lp = new LayoutPanel();
		lp.getElement().setId(mapLayoutPanelcssID);

		MapContainer.get().setParentPanel(lp);
		PopUpPanelContainer.get().setParentPanel(lp);
		SlideBarLeft.get().setParentPanel(lp);
		SlideBarRight.get().setParentPanel(lp);

//		Timer t = new EventInfoScheduler(PopUpPanelContainer.get());
//		t.scheduleRepeating(5000);

		// Fill Tabpanel
		add(lp, "Map");
		lp.getElement().getParentElement()
				.setId(mapLayoutPanelcssID + "Wrapper");
		add(MathPanel.get().getPanel(), "Statistics");
		MathPanel.get().getPanel().getElement().getParentElement()
				.setId(MathPanel.cssID + "Wrapper");

		MathPanel.get().getPanel().getElement().getParentElement().getStyle()
				.setHeight(Window.getClientHeight() - 90, Unit.PX);
		

		selectTab(0);
	}
}