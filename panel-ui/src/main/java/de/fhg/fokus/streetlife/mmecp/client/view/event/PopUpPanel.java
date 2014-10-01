package de.fhg.fokus.streetlife.mmecp.client.view.event;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.query.client.css.TextAlignProperty.TextAlign;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.fhg.fokus.streetlife.mmecp.client.model.IEventInfoDataMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.CSSDynamicData;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.SlideBar.STATUS;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.DtoToGWTElementMapper;
import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.sidebar.right.SlideBarRight;
import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public class PopUpPanel extends SiteElement<SimplePanel> implements
		ClickHandler {

	private EventInfo eventInfo = null;

	public PopUpPanel(EventInfo eventInfo) {
		super(new SimplePanel(), "eventinfo_" + eventInfo.getId(), "eventinfonotification alert-box notice");
		
		this.setEvent(eventInfo);
		getPanel().setSize(CSSDynamicData.PopUpPanelContainer_WIDTH_ROLL_IN + "px", CSSDynamicData.PopUpPanelContainer_HEIGHT + "px");

		$("#" + getPanel().getElement().getId()).css(
				CSS.TEXT_ALIGN.with(TextAlign.LEFT));
		getPanel().addDomHandler(this, ClickEvent.getType());
		getPanel().add(new Label(eventInfo.getId() + ""));
	}

	public EventInfo getEvent() {
		return eventInfo;
	}

	public void setEvent(EventInfo event) {
		this.eventInfo = event;
	}

	public void onClick(ClickEvent event) {
		PopUpPanelContainer.get().hideNotification(eventInfo);

		// Show futher information in right sidebar
		VerticalPanel content = SlideBarRight.get().getContent();
		content.clear();
		IEventInfoDataMapper eventInfo = DtoToGWTElementMapper.map(getEvent());
		eventInfo.fillContent(content);
		SlideBarRight.get().setStatus(STATUS.OPEN);
	}

	public void show() {
		$("#" + getPanel().getElement().getId()).animate(
				"width:" + CSSDynamicData.PopUpPanelContainer_WIDTH_ROLL_OUT, 200);
	}

	public void hide() {
		$("#" + getPanel().getElement().getId()).animate(
				"width:" + CSSDynamicData.PopUpPanelContainer_WIDTH_ROLL_IN, 200);
	}
}
