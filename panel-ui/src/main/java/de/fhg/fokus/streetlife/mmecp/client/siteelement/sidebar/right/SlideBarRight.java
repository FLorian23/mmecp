package de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.right;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoService;
import de.fhg.fokus.streetlife.mmecp.client.service.EventInfoServiceAsync;
import de.fhg.fokus.streetlife.mmecp.client.siteelement.sidebar.SlideBar;
import de.fhg.fokus.streetlife.mmecp.dto.EventInfo;

public class SlideBarRight extends SlideBar implements AsyncCallback<EventInfo> {

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
		add(content);

		EventInfoServiceAsync eventInfoService = GWT
				.create(EventInfoService.class);
		eventInfoService.getEventInfo(this);
	}

	@Override
	public void open() {
	}

	@Override
	public void close() {
	}

	public void onFailure(Throwable caught) {
		Window.alert("OnFailure-Error: " + caught.getMessage());
	}

	public void onSuccess(EventInfo result) {
		content.clear();
		IEventInfoView eventInfo = DtoToGWTElementMapper.map(result);
		eventInfo.fillContent(content);
	}
}