package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.GuidancePopUp;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.SlideBar;

public class SlideBarRight extends SlideBar {

	private static SlideBarRight slideBarRight = null;
	private VerticalPanel content = new VerticalPanel();

	public static SlideBarRight get() {
		if (slideBarRight == null) {
			slideBarRight = new SlideBarRight();
		}
		return slideBarRight;
	}

	public SlideBarRight() {
		super("#slideRightPanel", "#wrapperRightMenu");
		add(getContent());
	}

	@Override
	public void open() {
		// Show the SlideBar different as the close Slidebar
	}

	@Override
	public void close() {
		// analog to open
	}

	public void opening() {
		if (getStatus() == STATUS.OPEN)
			return;
		setStatus(STATUS.OPEN);
		$(getWrapper()).animate("width:" + SlideBar.WIDTH_ROLL_OUT, 500);
		PopUpPanelContainer.get().move(
				SlideBar.WIDTH_ROLL_OUT - SlideBar.WIDTH_ROLL_IN);
		
		if (GuidancePopUp.get().getCurrentX() + GuidancePopUp.get().guidancePopUp_WIDTH > 
		GuidancePopUp.get().getLayoutPanel().getElement().getClientWidth() - WIDTH_ROLL_OUT)
			GuidancePopUp.get().setTo(
					GuidancePopUp.get().getLayoutPanel().getElement().getClientWidth() - WIDTH_ROLL_OUT - GuidancePopUp.get().guidancePopUp_WIDTH - 50,
					GuidancePopUp.get().getCurrentY());
	}

	public void closing() {
		if (getStatus() == STATUS.CLOSE)
			return;
		setStatus(STATUS.CLOSE);
		$(getWrapper()).animate("width:" + SlideBar.WIDTH_ROLL_IN, 500);
		int x = SlideBar.WIDTH_ROLL_OUT - SlideBar.WIDTH_ROLL_IN;
		PopUpPanelContainer.get().move(-x);
	}

	public VerticalPanel getContent() {
		return content;
	}

	public void setContent(VerticalPanel content) {
		this.content = content;
	}
}