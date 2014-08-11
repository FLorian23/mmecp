package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.left;

import com.google.gwt.user.client.ui.Label;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.SlideBar;

public class SlideBarLeft extends SlideBar {

	private static SlideBarLeft slideBarLeft = null;

	public static SlideBarLeft get() {
		if (slideBarLeft == null) {
			slideBarLeft = new SlideBarLeft();
		}
		return slideBarLeft;
	}

	public SlideBarLeft() {
		super("#slideLeftPanel", "#wrapperLeftMenu");
	}

	@Override
	public void open() {
		clear();
		add(new Label("close"));
		
	}

	@Override
	public void close() {
		clear();
		add(new Label("open"));
	}
}
