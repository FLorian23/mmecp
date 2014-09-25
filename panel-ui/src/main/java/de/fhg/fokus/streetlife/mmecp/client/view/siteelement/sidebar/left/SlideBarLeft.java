package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.left;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.user.client.ui.Label;

import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar.STATUS;

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

	public void opening() {
		if (getStatus() == STATUS.OPEN) return;
		setStatus(STATUS.OPEN);
		$("#" + getWrapperID()).animate("width:" + SlideBar.WIDTH_ROLL_OUT, 500);
	}

	public void closing() {
		if (getStatus() == STATUS.CLOSE) return;
		setStatus(STATUS.CLOSE);
		$("#" + getWrapperID()).animate("width:" + SlideBar.WIDTH_ROLL_IN, 500);
	}
}
