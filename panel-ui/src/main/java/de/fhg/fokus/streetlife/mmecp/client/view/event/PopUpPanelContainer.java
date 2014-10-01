package de.fhg.fokus.streetlife.mmecp.client.view.event;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.client.view.siteelement.SiteElement;
import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public class PopUpPanelContainer extends SiteElement<VerticalPanel> implements
		EventNotificationReceiver {

	public static PopUpPanelContainer instance;
	private int currentHight = 0;

	ArrayList<PopUpPanel> notifications = new ArrayList<PopUpPanel>();

	private PopUpPanelContainer() {
		super(new VerticalPanel(), "PopupPanelContainer", null);
	}

	public static PopUpPanelContainer get() {
		if (instance == null)
			instance = new PopUpPanelContainer();
		return instance;
	}

	public void newNotification(EventInfo event, int position) {
		PopUpPanel popUpPanel = new PopUpPanel(event);
		notifications.add(position, popUpPanel);

		// visual stuff
		currentHight = currentHight + 56;
		// $("#wrapperPopupPanel").css("height", currentHight + "");
		$("#" + get().getWrapperID()).css("height", currentHight + "");
		$("#" + get().getID()).css("height", currentHight + "");
		getPanel().insert(popUpPanel.getPanel(), 0);
		popUpPanel.show();
	}

	public void hideNotification(PopUpPanel p) {
		currentHight = currentHight - 56;
		$("#eventinfo_" + p.getEvent().getId()).hide();
		// $("#wrapperPopupPanel").css("height", currentHight + "");
		$("#" + get().getWrapperID()).css("height", currentHight + "");
		$("#" + get().getID()).css("height", currentHight + "");
		getPanel().remove(p.getPanel());
	}

	public void hideNotification(EventInfo event) {
		for (int i = 0; i < notifications.size(); i++) {
			if (event == notifications.get(i).getEvent())
				hideNotification(notifications.get(i));
		}
	}

	public void move(int x) {
		// $("#wrapperPopupPanel").css("margin-right", marginRight + "");
		$("#" + get().getWrapperID()).animate("margin-right:" + x, 500);
	}
}
