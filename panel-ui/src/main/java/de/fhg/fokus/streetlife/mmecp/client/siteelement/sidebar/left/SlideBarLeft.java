package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.left;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.user.client.ui.Label;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.SlideBar;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.SlideBar.STATUS;

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

	public void opening() {
		if (getStatus() == STATUS.OPEN) return;
		setStatus(STATUS.OPEN);
		$(getWrapper()).animate("width:" + SlideBar.WIDTH_ROLL_OUT, 500);
	}

	public void closing() {
		if (getStatus() == STATUS.CLOSE) return;
		setStatus(STATUS.CLOSE);
		$(getWrapper()).animate("width:" + SlideBar.WIDTH_ROLL_IN, 500);
	}
}
