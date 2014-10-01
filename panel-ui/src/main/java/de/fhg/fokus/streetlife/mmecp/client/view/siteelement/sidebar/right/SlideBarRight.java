package de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.Res;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.event.PopUpPanelContainer;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar;

public class SlideBarRight extends SlideBar {

	private static SlideBarRight slideBarRight = null;

	public static SlideBarRight get() {
		if (slideBarRight == null) {
			slideBarRight = new SlideBarRight();
		}
		return slideBarRight;
	}

	public SlideBarRight() {
		super("slideRightPanel", null, new SiteElement<VerticalPanel>(
				new VerticalPanel(), "SlideBarRightContentPanel", null), true);
		getPicturePanel().addDomHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				if (getStatus() == STATUS.CLOSE) {
					setStatus(STATUS.OPEN);
				} else {
					setStatus(STATUS.CLOSE);
				}
			}
		}, ClickEvent.getType());
		
		getContentPanelSiteElement().addWidgetToPanel(new Label(CSSDynamicData.SlideBarRightInfoLabel), "slideBarinfoLabel", null);
	}

	protected void open() {
		if (getStatus() == STATUS.OPEN)
			return;
		$("#" + getWrapperID())
				.animate("width:" + CSSDynamicData.SlideBarRight_WIDTH_ROLL_OUT, 500);
		PopUpPanelContainer.get().move(CSSDynamicData.SlideBarRight_WIDTH_ROLL_OUT);
	}

	protected void close() {
		if (getStatus() == STATUS.CLOSE)
			return;
		$("#" + getWrapperID()).animate("width:" + CSSDynamicData.SlideBarRight_WIDTH_ROLL_IN, 500);
		PopUpPanelContainer.get().move(CSSDynamicData.SlideBarRight_WIDTH_ROLL_IN);
	}

	public VerticalPanel getContent() {
		return content.getPanel();
	}

	@Override
	protected ImageResource getClosePicture() {
		return Res.INSTANCE.arrowRight();
	}

	@Override
	protected ImageResource getOpenPicture() {
		return Res.INSTANCE.arrowLeft();
	}

	public int getCurrentWidth() {
		switch (getStatus()) {
		case OPEN:
			return CSSDynamicData.SlideBarRight_WIDTH_ROLL_OUT;
		case CLOSE:
			return CSSDynamicData.SlideBarRight_WIDTH_ROLL_IN;
		default:
			return 0;
		}
	}
}