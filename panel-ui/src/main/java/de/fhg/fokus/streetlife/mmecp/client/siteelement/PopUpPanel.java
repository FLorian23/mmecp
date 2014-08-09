package de.fhg.fokus.streetlife.mmecp.client.siteelement;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class PopUpPanel extends SimplePanel {

	private static PopUpPanel instance;
	public static final int WIDTH_ROLL_IN = 0;
	public static final int WIDTH_ROLL_OUT = 100;
	private String divWrapper = "wrapperPopupPanel";
	int timetoShow = 3; // sec

	private PopUpPanel() {
		getElement().setId("PopupPanel");
		addDomHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				 $(divWrapper).animate("width:" + WIDTH_ROLL_IN, 500);
			}
		}, ClickEvent.getType());
	}

	public static PopUpPanel get() {
		if (PopUpPanel.instance == null)
			PopUpPanel.instance = new PopUpPanel();

		return PopUpPanel.instance;
	}

	public void newNotification(String text) {
		$(divWrapper).animate("width:" + WIDTH_ROLL_OUT, 250);
		add(new Label(text));
	}

	public String getDivWrapper() {
		return divWrapper;
	}

	public void setDivWrapper(String divWrapper) {
		this.divWrapper = divWrapper;
	}
}
