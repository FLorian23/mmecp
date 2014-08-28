package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import static com.google.gwt.query.client.GQuery.$;

import java.util.ArrayList;

import com.google.gwt.query.client.css.CSS;
import com.google.gwt.query.client.css.TextAlignProperty.TextAlign;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fhg.fokus.streetlife.mmecp.share.dto.EventInfo;

public class PopUpPanelContainer extends VerticalPanel {

	public static PopUpPanelContainer instance;

	public static final int WIDTH_ROLL_IN = 0;
	public static final int WIDTH_ROLL_OUT = 100;
	private int currentHight = 0;
	
	ArrayList<PopUpPanel> notifications = new ArrayList<PopUpPanel>();

	private PopUpPanelContainer() {
		setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		getElement().setId("PopupPanelContainer");
		$("#" + getElement().getId()).css(CSS.TEXT_ALIGN.with(TextAlign.RIGHT));

		Timer t = new EventInfoScheduler();

		t.scheduleRepeating(5000);
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
		$("#wrapperPopupPanel").css("height", currentHight + "");
		$("#PopupPanelContainer").css("height", currentHight + "");
		insert(popUpPanel, 0);
		popUpPanel.show();
	}

	public void hideNotification(PopUpPanel p) {
		currentHight = currentHight - 56;
		$("#eventinfo_" + p.getEvent().getId()).hide();
		$("#wrapperPopupPanel").css("height", currentHight + "");
		$("#PopupPanelContainer").css("height", currentHight + "");
		remove(p);
	}

	public void hideNotification(EventInfo event) {
		for (int i = 0; i < notifications.size(); i++) {
			if (event == notifications.get(i).getEvent())
				hideNotification(notifications.get(i));
		}
	}

	public void move(int x) {
		// $("#wrapperPopupPanel").css("margin-right", marginRight + "");
		$("#wrapperPopupPanel").animate("margin-right:" + x, 500);
	}
}
