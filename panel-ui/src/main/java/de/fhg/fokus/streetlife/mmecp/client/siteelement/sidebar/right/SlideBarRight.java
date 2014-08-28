package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.user.client.ui.VerticalPanel;

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
				SlideBar.WIDTH_ROLL_OUT);
	}

	public void closing() {
		if (getStatus() == STATUS.CLOSE)
			return;
		setStatus(STATUS.CLOSE);
		$(getWrapper()).animate("width:" + SlideBar.WIDTH_ROLL_IN, 500);
		PopUpPanelContainer.get().move(SlideBar.WIDTH_ROLL_IN);
	}
	
	public void hide(){
		if (getStatus() == STATUS.OPEN)
			return;
		
		setStatus(STATUS.HIDE);
		$(getWrapper()).animate("width:" + 0, 0);
		PopUpPanelContainer.get().move(0);
	}

	public VerticalPanel getContent() {
		return content;
	}

	public void setContent(VerticalPanel content) {
		this.content = content;
	}
}