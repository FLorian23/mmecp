package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.query.client.css.TextAlignProperty.TextAlign;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right.DtoToGWTElementMapper;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right.IEventInfoView;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right.SlideBarRight;
import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public class PopUpPanel extends SimplePanel implements ClickHandler {

	private EventInfo eventInfo = null;

	public PopUpPanel(EventInfo eventInfo) {
		this.setEvent(eventInfo);
		this.setSize(PopUpPanelContainer.WIDTH_ROLL_IN + "px", "50px");
		getElement().setId("eventinfo_" + eventInfo.getId());
		getElement().setClassName("eventinfonotification");
		getElement().addClassName("alert-box");
		getElement().addClassName("notice");
		$("#" + getElement().getId()).css(CSS.TEXT_ALIGN.with(TextAlign.LEFT));
		addDomHandler(this, ClickEvent.getType());
		add(new Label(eventInfo.getId() + ""));
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
		IEventInfoView eventInfo = DtoToGWTElementMapper.map(getEvent());
		eventInfo.fillContent(content);
		SlideBarRight.get().opening();
	}

	public void show() {
		$("#" + getElement().getId()).animate(
				"width:" + PopUpPanelContainer.WIDTH_ROLL_OUT, 200);
	}

	public void hide() {
		$("#" + getElement().getId()).animate(
				"width:" + PopUpPanelContainer.WIDTH_ROLL_IN, 200);
	}
}
