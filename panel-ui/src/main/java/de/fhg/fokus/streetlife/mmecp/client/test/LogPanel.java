package de.fhg.fokus.streetlife.mmecp.client.test;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LogPanel extends VerticalPanel {

	private static LogPanel instance = null;

	private LogPanel() {

	}

	public static LogPanel get() {
		if (instance == null)
			instance = new LogPanel();
		return instance;
	}

	public void newMessage(String s) {
		add(new Label(s));
	}
}
